package ee.forgr.plugin.screenrecorder;

import static java.nio.file.Files.size;


import android.media.MediaRecorder;
import android.os.Build;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import dev.bmcreations.scrcast.ScrCast;
import dev.bmcreations.scrcast.config.Options;
import dev.bmcreations.scrcast.config.VideoConfig;
import dev.bmcreations.scrcast.recorder.RecordingCallbacks;
import dev.bmcreations.scrcast.recorder.RecordingState;



@CapacitorPlugin(name = "ScreenRecorder")
public class ScreenRecorderPlugin extends Plugin {

    private ScrCast recorder;
    private byte[] video;


    @Override
    public void load() {
        recorder =  ScrCast.use(this.bridge.getActivity());
        VideoConfig config = new VideoConfig(1920, 1080,MediaRecorder.VideoEncoder.H264, 8_000_00, 60,7200);
        Options options = new Options(config);
        recorder.updateOptions(options);
        recorder.setRecordingCallback(new RecordingCallbacks() {

            @Override
            public void onStateChange(@NonNull RecordingState recordingState) {

            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onRecordingFinished(@NonNull File file) {
                try {
                    byte[] bytes = Files.readAllBytes(file.toPath());
                    video = bytes;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @PluginMethod
    public void start(PluginCall call) {
        recorder.record();
        call.resolve();
    }

    @PluginMethod
    public void stop(PluginCall call) {
        recorder.stopRecording();
        call.resolve();
    }
    @PluginMethod
    public void getVideo(PluginCall call) throws InterruptedException, JSONException {
        Thread.sleep(100);
        JSObject ret = new JSObject();
        ret.put("video", new String(Base64.encode(video, Base64.NO_WRAP)));
        call.resolve(ret);
    }
}
