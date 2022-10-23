package ee.forgr.plugin.screenrecorder;

import android.media.MediaRecorder;

import androidx.annotation.NonNull;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.File;

import dev.bmcreations.scrcast.ScrCast;
import dev.bmcreations.scrcast.config.Options;
import dev.bmcreations.scrcast.config.VideoConfig;
import dev.bmcreations.scrcast.recorder.RecordingCallbacks;
import dev.bmcreations.scrcast.recorder.RecordingState;


@CapacitorPlugin(name = "ScreenRecorder")
public class ScreenRecorderPlugin extends Plugin {

    private ScrCast recorder;
    private File video;


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

            @Override
            public void onRecordingFinished(@NonNull File file) {
                video = file;
            }
        });
    }

    @PluginMethod
    public void start(PluginCall call) {
        recorder.record();
        call.resolve();
    }

    @PluginMethod
    public File stop(PluginCall call) {
        recorder.stopRecording();
        call.resolve();
        return video;
    }
}
