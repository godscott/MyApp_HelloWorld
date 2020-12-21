package com.cht.cyhsieh.myapp_helloworld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Future;

import net.ypresto.androidtranscoder.MediaTranscoder;
import net.ypresto.androidtranscoder.format.MediaFormatStrategyPresets;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.Window;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoCompression extends Activity {
	private static final String TAG = VideoCompression.class.getCanonicalName();

	public static final int CAPTURE_VIDEO = 168;
	public static final int SELECT_VIDEO = 188;
	public static final int VIDEO_COMPRESSION = 198;
	
	private VideoView mVideoView;
	private ProgressBar mProgressBar;
	
	private Uri captureFileUri;
	
	private static final int PROGRESS_BAR_MAX = 1000;
    private Future<Void> mFuture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_video_compression);
		
//		int mode = this.getIntent().getIntExtra("CHAT_FUN", 0);
		
		mVideoView = (VideoView) this.findViewById(R.id.view_video_compression_videoView_videoFile);
		mProgressBar = (ProgressBar) this.findViewById(R.id.view_video_compression_progressbar);
		
//		Intent intent = null;
//		if(mode==CAPTURE_VIDEO){
//			intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//            File fvideo = new File(getOutMediaVideoPath(), createFileName(GENVIDEO));
//            //modify by cy at 105/03/09 for fix samsuang phone crash
//            captureFileUri = Uri.fromFile(fvideo);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, captureFileUri);
//            //end by cy at 105/03/09 for fix samsuang phone crash
//            startActivityForResult(intent, mode);
//		}
//		else if(mode==SELECT_VIDEO){
//			intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            intent.setType("video/*");
//            startActivityForResult(Intent.createChooser(intent, "��ܼv��"), mode);
//		}
		
		String videoPath = this.getIntent().getStringExtra("videopath");
		String capturePicPath = videoPath.replace(".mp4", ".jpg");
		final File fileVideoView = new File(capturePicPath);
		
		Bitmap thumbnail = null;
		if(fileVideoView.exists())
			thumbnail = BitmapFactory.decodeFile(fileVideoView.getPath());

		BitmapDrawable mbmDrawable = new BitmapDrawable(thumbnail);
		mVideoView.setBackgroundDrawable(mbmDrawable);
		
//		mc = new MediaController(this);   
//		  
//		mVideoView.setMediaController(mc); 
//		  
//		  try { 
//			  mVideoView.setVideoPath(videoPath);     
//	        	
//          }catch(Exception e) {
//          	
//          	Log.d("Videos Start", e.toString());  
//          	
//          }
		
		
		final File infile = new File(videoPath);
		final File outfile;
		outfile = new File(getOutMediaVideoPath(), createFileName(GENVIDEO));
//		try {
//			file = File.createTempFile("transcode_test", ".mp4", getExternalFilesDir(null));
//		} 
//		catch (IOException e) {
//            Log.e(TAG, "Failed to create temporary file.", e);
//            Toast.makeText(this, "Failed to create temporary file.", Toast.LENGTH_LONG).show();
//            return;
//        }
		
//        ContentResolver resolver = getContentResolver();
//        final ParcelFileDescriptor parcelFileDescriptor;
//        try {
//            parcelFileDescriptor = resolver.openFileDescriptor(data.getData(), "r");
//        } 
//        catch (FileNotFoundException e) {
//            Log.w("Could not open '" + data.getDataString() + "'", e);
//            Toast.makeText(TranscoderActivity.this, "File not found.", Toast.LENGTH_LONG).show();
//            return;
//        }
//        final FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		mProgressBar.setMax(PROGRESS_BAR_MAX);
        final long startTime = SystemClock.uptimeMillis();
        MediaTranscoder.Listener listener = new MediaTranscoder.Listener() {
             @Override
             public void onTranscodeProgress(double progress) {
                 if (progress < 0) {
                	 mProgressBar.setIndeterminate(true);
                 } else {
                	 mProgressBar.setIndeterminate(false);
                	 mProgressBar.setProgress((int) Math.round(progress * PROGRESS_BAR_MAX));
                 }
             }
             @Override
             public void onTranscodeCompleted() {
            	 Log.d(TAG, "transcoding took " + (SystemClock.uptimeMillis() - startTime) + "ms");
//            	 onTranscodeFinished(true, "transcoded file placed on " + file, parcelFileDescriptor);
            	 onTranscodeFinished(true, "transcoded file placed on " + outfile, null);
//            	 startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType(Uri.fromFile(outfile), "video/mp4"));
            	 
            	 if(infile.exists())
            		 infile.delete();
            	 if(fileVideoView.exists())
            		 fileVideoView.delete();
            	 Intent intent = new Intent();
            	 Bundle bundle = new Bundle();
            	 bundle.putString("finalVideoPath", outfile.getAbsolutePath());
            	 intent.putExtras(bundle);
            	 VideoCompression.this.setResult(RESULT_OK, intent);
            	 finish();
             }

             @Override
             public void onTranscodeCanceled() {
//                 onTranscodeFinished(false, "Transcoder canceled.", parcelFileDescriptor);
            	 onTranscodeFinished(false, "Transcoder canceled.", null);
             }

             @Override
             public void onTranscodeFailed(Exception exception) {
//                 onTranscodeFinished(false, "Transcoder error occurred.", parcelFileDescriptor);
            	 onTranscodeFinished(false, "Transcoder error occurred.", null);
            	 
            	 if(outfile.exists())
            		 outfile.delete();
            	 if(fileVideoView.exists())
            		 fileVideoView.delete();
            	 Intent intent = new Intent();
            	 Bundle bundle = new Bundle();
            	 bundle.putString("finalVideoPath", infile.getAbsolutePath());
            	 intent.putExtras(bundle);
            	 VideoCompression.this.setResult(RESULT_OK, intent);
            	 finish();
             }
          };
          Log.d(TAG, "transcoding into " + outfile);
//          mFuture = MediaTranscoder.getInstance().transcodeVideo(fileDescriptor, file.getAbsolutePath(),
//                 MediaFormatStrategyPresets.createAndroid720pStrategy(), listener);
          
          try {
			mFuture = MediaTranscoder.getInstance().transcodeVideo(videoPath, outfile.getAbsolutePath(),
			          MediaFormatStrategyPresets.createAndroid720pStrategy(), listener);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          switchButtonEnabled(true);
	}
	
//	private MediaController mc;
	
//	private void onTranscodeFinished(boolean isSuccess, String toastMessage, ParcelFileDescriptor parcelFileDescriptor) {
////        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//		mProgressBar.setIndeterminate(false);
//		mProgressBar.setProgress(isSuccess ? PROGRESS_BAR_MAX : 0);
//        switchButtonEnabled(false);
//        Toast.makeText(VideoCompression.this, toastMessage, Toast.LENGTH_LONG).show();
//        try {
//            parcelFileDescriptor.close();
//        } catch (IOException e) {
//            Log.w("Error while closing", e);
//        }
//    }
	
	private void onTranscodeFinished(boolean isSuccess, String toastMessage, ParcelFileDescriptor parcelFileDescriptor) {
//      final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		mProgressBar.setIndeterminate(false);
		mProgressBar.setProgress(isSuccess ? PROGRESS_BAR_MAX : 0);
      switchButtonEnabled(false);
      Toast.makeText(VideoCompression.this, toastMessage, Toast.LENGTH_LONG).show();
  }

    private void switchButtonEnabled(boolean isProgress) {
//        findViewById(R.id.select_video_button).setEnabled(!isProgress);
//        findViewById(R.id.cancel_button).setEnabled(isProgress);
    }
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
//		if (resultCode == Activity.RESULT_OK) {
//			File destFile = null;
//            if (requestCode == CAPTURE_VIDEO) {
//            	Uri videouri = captureFileUri;//data.getData();
//            	Log.d(TAG, String.format("Capture Video file path = %s", videouri.getPath()));
//            	
//            	File srcFile = new File(videouri.getPath());
//            	destFile = srcFile;
//	            getVideoFirstFrame(destFile);
//            }
//            else if (requestCode == SELECT_VIDEO) {
//            	Uri selectedVideoUri = data.getData();
//                Log.d(TAG, String.format("Video VideoUri = %s", selectedVideoUri.toString()));
//                
//                String video_Path = getSystemMediaPath(selectedVideoUri, VideoCompression.this, GENVIDEO);
//                Log.d(TAG, String.format("Video Path = %s", video_Path));
//
//                File srcFile = new File(video_Path);
//	            destFile = new File(getOutMediaVideoPath(), srcFile.getName());
//	            if(!destFile.getPath().equalsIgnoreCase(srcFile.getPath()))
//	            	copyfile(srcFile, destFile);
//
//	            getVideoFirstFrame(destFile);
//            }
//            
//            Bitmap thumbnail = null;
//			if(destFile.exists())
//				thumbnail = BitmapFactory.decodeFile(destFile.getPath());
//
//			BitmapDrawable mbmDrawable = new BitmapDrawable(thumbnail);
//			mVideoView.setBackgroundDrawable(mbmDrawable);
//		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	//====================================================================
	public static final int GENPICTURE = 0;
	public static final int GENVIDEO  = 1;
	
	public static String strOutMediaPath = Environment.getExternalStorageDirectory() + File.separator + "VideoCompression";
	
	static final DateFormat sDateFullFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
	public static String getFullDateString(final Date date, final TimeZone timeZone){
		sDateFullFormat.setTimeZone(timeZone);
		return sDateFullFormat.format(date);
	}
	
	public String createFileName(int mode){
		String filename = "unknown";
		if(mode == GENVIDEO){
			filename = String.format("%s.mp4", getFullDateString(new Date(), TimeZone.getDefault()));
		}
		
		return filename;
	}
	
	public static String getOutMediaVideoPath(){
		File MediaPath = new File(strOutMediaPath + File.separator + "Video");
        if(!MediaPath.exists())
        	MediaPath.mkdirs();
        return MediaPath.getAbsolutePath();
	}
	
	public static boolean getVideoFirstFrame(File file){
		Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(file.getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
		String videoPhotoName = file.getName();
		videoPhotoName = videoPhotoName.replace(".mp4", ".jpg");
		Log.d(TAG, String.format("getVideoFirstFrame, videoName = %s, pictureName = %s", file.getName(), videoPhotoName));
		
		File picture_file = new File(getOutMediaVideoPath(), videoPhotoName);
    	Log.d(TAG, String.format("VideoPicture file path = %s", picture_file.getAbsoluteFile()));
    	OutputStream fOut = null;
    	try {
            fOut = new FileOutputStream(picture_file);
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    	return true;
	}
	
	public String getSystemMediaPath(Uri uri, Activity activity, int SelMethod) {
		String[] projection = (SelMethod==GENPICTURE) ? new String[]{ MediaColumns.DATA } : new String[]{MediaStore.Video.VideoColumns.DATA};
	    //modify by cy at 104/08/10
//		Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		//end by cy
		int column_index = cursor.getColumnIndexOrThrow( (SelMethod==GENPICTURE) ? MediaColumns.DATA : MediaStore.Video.VideoColumns.DATA);
		cursor.moveToFirst();    	
		return cursor.getString(column_index);
	}
	
	public static boolean copyfile(File srcFile, File destFile){
//    	File src = new File(srcFile);
//    	File dest = new File(destFile);
    	
    	try {
			InputStream in = new FileInputStream(srcFile);
			OutputStream out = new FileOutputStream(destFile);
			
			byte [] buf = new byte[1024];
		    int len;
			while ((len = in.read(buf)) > 0){
				out.write(buf, 0, len);
			}

			in.close();
			out.close();
			Log.d(TAG, "File Copy finish");
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
    
    public static boolean copyfile(File srcFile, FileOutputStream out){
//    	File src = new File(srcFile);
//    	File dest = new File(destFile);
    	
    	try {
			InputStream in = new FileInputStream(srcFile);
//			OutputStream out = new FileOutputStream(destFile);
			
			byte [] buf = new byte[1024];
		    int len;
			while ((len = in.read(buf)) > 0){
				out.write(buf, 0, len);
			}

			in.close();
			out.close();
			Log.d(TAG, "File Copy finish");
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
	
}
