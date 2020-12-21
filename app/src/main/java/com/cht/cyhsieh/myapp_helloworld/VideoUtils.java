package com.cht.cyhsieh.myapp_helloworld;

import android.content.ContentResolver;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Toast;

import net.ypresto.androidtranscoder.MediaTranscoder;
import net.ypresto.androidtranscoder.format.MediaFormatStrategyPresets;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Future;

public class VideoUtils {
    private static final String TAG = VideoUtils.class.getCanonicalName();
    private Future<Void> mFuture;

//    public boolean videoTranscoder() {
//        boolean isFinish = false;
//        final File file;
//
//        try {
//            File outputDir = new File(getExternalFilesDir(null), "outputs");
//            //noinspection ResultOfMethodCallIgnored
//            outputDir.mkdir();
//            file = File.createTempFile("transcode_test", ".mp4", outputDir);
//        } catch (IOException e) {
//            Log.e(TAG, "Failed to create temporary file.", e);
//            return false;
//        }
//        ContentResolver resolver = getContentResolver();
//        final ParcelFileDescriptor parcelFileDescriptor;
//        try {
//            parcelFileDescriptor = resolver.openFileDescriptor(data.getData(), "r");
//        } catch (FileNotFoundException e) {
//            Log.w("Could not open '" + data.getDataString() + "'", e);
//            return false;
//        }
//        final FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
////        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
////        progressBar.setMax(PROGRESS_BAR_MAX);
////        final long startTime = SystemClock.uptimeMillis();
//        MediaTranscoder.Listener listener = new MediaTranscoder.Listener() {
//            @Override
//            public void onTranscodeProgress(double progress) {
////                if (progress < 0) {
////                    progressBar.setIndeterminate(true);
////                } else {
////                    progressBar.setIndeterminate(false);
////                    progressBar.setProgress((int) Math.round(progress * PROGRESS_BAR_MAX));
////                }
//            }
//
//            @Override
//            public void onTranscodeCompleted() {
////                Log.d(TAG, "transcoding took " + (SystemClock.uptimeMillis() - startTime) + "ms");
//                onTranscodeFinished(true, "transcoded file placed on " + file, parcelFileDescriptor);
////                Uri uri = FileProvider.getUriForFile(TranscoderActivity.this, FILE_PROVIDER_AUTHORITY, file);
////                startActivity(new Intent(Intent.ACTION_VIEW)
////                        .setDataAndType(uri, "video/mp4")
////                        .setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION));
//            }
//
//            @Override
//            public void onTranscodeCanceled() {
//                onTranscodeFinished(false, "Transcoder canceled.", parcelFileDescriptor);
//            }
//
//            @Override
//            public void onTranscodeFailed(Exception exception) {
//                onTranscodeFinished(false, "Transcoder error occurred.", parcelFileDescriptor);
//            }
//        };
//        Log.d(TAG, "transcoding into " + file);
//        mFuture = MediaTranscoder.getInstance().transcodeVideo(fileDescriptor, file.getAbsolutePath(),
//                MediaFormatStrategyPresets.createAndroid720pStrategy(8000 * 1000, 128 * 1000, 1), listener);
////        switchButtonEnabled(true);
//
//        return isFinish;
//    }

    private void onTranscodeFinished(boolean isSuccess, String toastMessage, ParcelFileDescriptor parcelFileDescriptor) {
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//        progressBar.setIndeterminate(false);
//        progressBar.setProgress(isSuccess ? PROGRESS_BAR_MAX : 0);
//        switchButtonEnabled(false);
//        Toast.makeText(TranscoderActivity.this, toastMessage, Toast.LENGTH_LONG).show();
        try {
            parcelFileDescriptor.close();
        } catch (IOException e) {
            Log.w("Error while closing", e);
        }
    }
}
