package hu.barbar.commonObjectsForGoogleWeatherReciever;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

/**
 * Contains "easy to use" and useful methods (routines) to resize Bitmap objects 
 * (get resized Bitmap, get resized and rotated Bitmap (you can specify the direction of rotation too..), 
 * get resized Bitmap to fit specified frame, get resized  and rotated Bitmap to fit specified frame (you can specify the direction of rotation too..),
 * get resized Bitmap to fill specified frame, get resized  and rotated Bitmap to fill specified frame (you can specify the direction of rotation too..),
 * and ALL these methods has getBitmapDrawable.... pair too.
 * 
 * @author Barbár
 *
 */
public class BitmapResizer {
	
    
	/** Static constant to define direction of clockwise rotation*/
	public static final int ROTATION_CLOCKWISE = 1;
	
	/** Static constant to define direction of counterclockwise rotation*/
	public static final int ROTATION_COUNTER_CLOCKWISE = -1;
	
	
	/** Static constant.
	 * If use this to wantedWidth or wantedHeight of any "getResized(...)" method it will use the original size
	 */
	public static final int KEEP_ORIGINAL_DIMENSION = -1;
	
	
	
	/** The LogCat tag of this class */
 	private static final String TAG = "BitmapResizer";

 	
 	
	/** Define the default cutting mode in "fillFrame" methods. (You can change it) */ 
	public static int DEFATULT_CUTTING_MODE = 0;
	/** Define "top left" cutting position in "fillFrame" methods. */
	public static final int CUT_NONE = 0;
	/** Define the horizontal center position in "fillFrame" methods. */
	public static final int CUT_CENTER_HORIZONTAL = 1;
	/** Define the vertical center position in "fillFrame" methods. */
	public static final int CUT_CENTER_VERTICAL = 2;
	/** Define the horizontal AND vertical center position in "fillFrame" methods. */
	public static final int CUT_CENTER = 3;
	
	
	
	
	// =================================================== RESIZE ===================================================
	// Returns Bitmap
	
	/**
	 * Resize the given bitmap to given size
	 * @param originalBitmap the original bitmap with original size
	 * @param wantedWidth the new width of result bitmap what we want.
	 * @param wantedHeight the new height of result bitmap what we want.
	 * @return the result bitmap with new size
	 */
	public static Bitmap getResized(Bitmap originalBitmap, int wantedWidth, int wantedHeight){
		return getResized(originalBitmap, wantedWidth, wantedHeight, 0f);
	}
	
	
	/**
	 * Resize the given bitmap to given size and rotate it with given degrees
	 * @param originalBitmap the original bitmap with original size
	 * @param wantedWidth the new width of result bitmap what we want.
	 * @param wantedHeight the new height of result bitmap what we want.
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap
	 * @return the result bitmap with new size and rotated with given degrees
	 */
    public static Bitmap getResized(Bitmap originalBitmap, int wantedWidth, int wantedHeight, float rotationInDegree){
    	
    	int orgWidth = originalBitmap.getWidth();
    	int orgHeight = originalBitmap.getHeight();
    	
    	
    	// keep original size (if it wanted)
    	
    	int resultWidth;
    	int resultHeight;
    	
    	
    	if(wantedWidth == KEEP_ORIGINAL_DIMENSION)
    		resultWidth = orgWidth;
    	else
    		resultWidth = wantedWidth;
    	
    	if(wantedHeight == KEEP_ORIGINAL_DIMENSION)
    		resultHeight = orgHeight;
    	else
    		resultHeight = wantedHeight;
    	
    	
    	
    	// calculate the scale
        float scaleWidth = ((float) resultWidth) / orgWidth;
        float scaleHeight = ((float) resultHeight) / orgHeight;
        
        // createa matrix for the manipulation
        Matrix matrix = new Matrix();
        
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        
        // rotate the Bitmap
        matrix.postRotate(rotationInDegree);
        
        // create the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0,
        						orgWidth, orgHeight, matrix, true);
    	
    	return resizedBitmap;
    }
    
    
    /**
	 * Resize the given bitmap to given size and rotate it with given degrees to given direction
	 * @param originalBitmap the original bitmap with original size
	 * @param wantedWidth the new width of result bitmap what we want.
	 * @param wantedHeight the new height of result bitmap what we want.
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap
	 * @param directionOfRotation can be clockwise (BitmapResizer.ROTATION_CLOCKWISE) or counterclockwise (BitmapResizer.ROTATION_COUNTER_CLOCKWISE) its means the direction of rotation
	 * @return the result bitmap with new size and rotated (to the given direction) with given degrees
	 */
    public static Bitmap getResized(Bitmap originalBitmap, int wantedWidth, int wantedHeight, float rotationInDegree, int directionOfRotation){
    	
    	if (directionOfRotation == ROTATION_COUNTER_CLOCKWISE){
    		return getResized(originalBitmap, wantedWidth, wantedHeight, (rotationInDegree*(-1)));
    	}
    	
		return getResized(originalBitmap, wantedWidth, wantedHeight, rotationInDegree);
    }
    
    
    
    //Returns BitmapDrawable
    
    /**
	 * Resize the given bitmap to given size  and rotate it with given degrees to given direction and "convert" the result to BitmapDrawable
	 * @param originalBitmap the original bitmap with original size
	 * @param wantedWidth the new width of result bitmap what we want.
	 * @param wantedHeight the new height of result bitmapDrawable what we want.
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap
	 * * @param directionOfRotation can be clockwise (BitmapResizer.ROTATION_CLOCKWISE) or counterclockwise (BitmapResizer.ROTATION_COUNTER_CLOCKWISE) its means the direction of rotation
	 * @return the result bitmapDrawable with new size and rotated (to the given direction) with given degrees
	 */
    public static BitmapDrawable getBitmapDrawableResized(Bitmap originalBitmap, int wantedWidth, int wantedHeight, float rotationInDegree, int directionOfRotation){
    	if (directionOfRotation == ROTATION_COUNTER_CLOCKWISE){
    		return getBitmapDrawableResized(originalBitmap, wantedWidth, wantedHeight, (rotationInDegree*(-1)));
    	}
    	
		return getBitmapDrawableResized(originalBitmap, wantedWidth, wantedHeight, rotationInDegree);
    }
    
    
    /**
	 * Resize the given bitmap to given size and rotate it with given degrees and "convert" the result to BitmapDrawable
	 * @param originalBitmap the original bitmap with original size
	 * @param wantedWidth the new width of result bitmap what we want.
	 * @param wantedHeight the new height of result bitmapDrawable what we want.
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap
	 * @return the result bitmapDrawable with new size and rotated with given degrees
	 */
    public static BitmapDrawable getBitmapDrawableResized(Bitmap originalBitmap, int wantedWidth, int wantedHeight, float rotationInDegree){
    	return new BitmapDrawable(getResized(originalBitmap, wantedWidth, wantedHeight, rotationInDegree));
    }
    
    
    /**
	 * Resize the given bitmap to given size and "convert" the result to BitmapDrawable
	 * @param originalBitmap the original bitmap with original size
	 * @param wantedWidth the new width of result bitmap what we want.
	 * @param wantedHeight the new height of result bitmapDrawable what we want.
	 * @return the result bitmapDrawable with new size and rotated with given degrees
	 */
    public static BitmapDrawable getBitmapDrawableResized(Bitmap originalBitmap, int wantedWidth, int wantedHeight){
    	return getBitmapDrawableResized(originalBitmap, wantedWidth, wantedHeight, 0f);
    }
	
    
    
    // =================================================== RESIZE (in) TO FRAME =================================================== 
    // Returns Bitmap
    
    /**
	 * Resize/Scale the given bitmap to fit in the specified frame and rotate it with given degrees to given direction. Note: if rotationDegrees == 90 or 180 then "fit to frame" calculation will use the rotated dimensions of bitmap 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap
	 * @param directionOfRotation can be clockwise (BitmapResizer.ROTATION_CLOCKWISE) or counterclockwise (BitmapResizer.ROTATION_COUNTER_CLOCKWISE) its means the direction of rotation
	 * @return the result bitmap what fit to specified frame and rotated (to the given direction) with given degrees
	 */
    public static Bitmap getResizedToFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, float rotationInDegree, int directionOfRotation){
    	
    	
    	Bitmap bm = originalBitmap;
    	
    	int wantedFrameWidth = frameWidth;
		int wantedFrameHeight = frameHeight;
    	
    	
    	
    	
    	
    	int originalWidth = bm.getWidth();
    	int originalHeight = bm.getHeight();
    	
    	if( (rotationInDegree == 90) || (rotationInDegree == 270) ){
	    	originalWidth = bm.getHeight();
	    	originalHeight = bm.getWidth();
    	}
    	
    	Log.i(TAG + " TO FRAME", "Resize TO FRAME orgWidth: " + Integer.toString(originalWidth));
    	Log.i(TAG + " TO FRAME", "Resize TO FRAME orgHeight " + Integer.toString(originalHeight));
    	
    	
    	
    	
    	
    	float widthRatio = (float) wantedFrameWidth / originalWidth;
    	float heightRatio = (float) wantedFrameHeight / originalHeight;
    	
    	Log.i(TAG + " TO FRAME", "Resize TO FRAME widthRatio: " + Float.toString(widthRatio));
    	Log.i(TAG + " TO FRAME", "Resize TO FRAME heightRatio " + Float.toString(heightRatio));
    	
    	
    	
    	
    	float smallerRatio;
    	
    	if(widthRatio < heightRatio)
    		smallerRatio = widthRatio;
    	else
    		smallerRatio = heightRatio;
    	
    	Log.i(TAG + " TO FRAME", "Smaller ratio: " + Float.toString(smallerRatio));
    	
    	
    	int ww = (int) (originalWidth * smallerRatio);
    	int wh = (int) (originalHeight * smallerRatio);
    	
    	Log.i(TAG + " TO FRAME", "Wanted width: " + Integer.toString(ww));
    	Log.i(TAG + " TO FRAME", "Wanted height: " + Integer.toString(wh));
    	
    	return getResized(bm, ww , wh, rotationInDegree, directionOfRotation);
    }
    
    
    /**
	 * Resize/Scale the given bitmap to fit in the specified frame and rotate it with given degrees. Note: if rotationDegrees == 90 or 180 then "fit to frame" calculation will use the rotated dimensions of bitmap 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap (Note: default rotation: CLOCKWISE)
	 * @return the result bitmap what fit to specified frame and rotated with given degrees
	 */
    public static Bitmap getResizedToFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, float rotationInDegree){
    	return getResizedToFrame(originalBitmap, frameWidth, frameHeight, rotationInDegree, ROTATION_CLOCKWISE);
    }
    
    
    /**
	 * Resize/Scale the given bitmap to fit in the specified frame. 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @return the result bitmap what fit to specified frame.
	 */
    public static Bitmap getResizedToFrame(Bitmap originalBitmap, int frameWidth, int frameHeight){
    	return getResizedToFrame(originalBitmap, frameWidth, frameHeight, 0, ROTATION_CLOCKWISE);
    }
    
    
    
    // Returns BitmapDrawable
    
    /**
	 * Resize/Scale the given bitmap to fit in the specified frame and rotate it with given degrees to given direction and "convert" the result to BitmapDrawable. Note: if rotationDegrees == 90 or 180 then "fit to frame" calculation will use the rotated dimensions of bitmap 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap
	 * @param directionOfRotation can be clockwise (BitmapResizer.ROTATION_CLOCKWISE) or counterclockwise (BitmapResizer.ROTATION_COUNTER_CLOCKWISE) its means the direction of rotation
	 * @return the result bitmapDrawable what fit to specified frame and rotated (to the given direction) with given degrees
	 */
    public static BitmapDrawable getBitmapDrawableResizedToFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, float rotationInDegree, int directionOfRotation){
    	return new BitmapDrawable(getResizedToFrame(originalBitmap, frameWidth, frameHeight, rotationInDegree, directionOfRotation));
    	
    }

    
    /**
	 * Resize/Scale the given bitmap to fit in the specified frame and rotate it with given degrees and "convert" the result to BitmapDrawable. Note: if rotationDegrees == 90 or 180 then "fit to frame" calculation will use the rotated dimensions of bitmap 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap (Note: default rotation: CLOCKWISE)
	 * @return the result bitmapDrawable what fit to specified frame and rotated with given degrees
	 */
    public static BitmapDrawable getBitmapDrawableResizedToFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, float rotationInDegree){
    	return new BitmapDrawable(getResizedToFrame(originalBitmap, frameWidth, frameHeight, rotationInDegree));
    }
    
    
    /**
	 * Resize/Scale the given bitmap to fit in the specified frame and "convert" the result to BitmapDrawable. 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @return the result bitmapDrawable what fit to specified frame.
	 */
    public static BitmapDrawable getBitmapDrawableResizedToFrame(Bitmap originalBitmap, int frameWidth, int frameHeight){
    	return new BitmapDrawable( getResizedToFrame(originalBitmap, frameWidth, frameHeight) );
    }
    
    
    
    
    // =================================================== RESIZE TO FILL FRAME ===================================================
    // Returns Bitmap
    
    /**
	 * Resize/Scale the given bitmap to fill the specified frame and rotate it with given degrees to given direction. Note: if rotationDegrees == 90 or 180 then "fit to frame" calculation will use the rotated dimensions of bitmap 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param cuttingMode means will be cut off from the projecting parts
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap
	 * @param directionOfRotation can be clockwise (BitmapResizer.ROTATION_CLOCKWISE) or counterclockwise (BitmapResizer.ROTATION_COUNTER_CLOCKWISE) its means the direction of rotation
	 * @return the result bitmap what fills the specified frame and rotated (to the given direction) with given degrees
	 */
    public static Bitmap getResizedToFillFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, int cuttingMode, float rotationInDegree, int directionOfRotation){
    	int originalWidth, originalHeight;
    	
    	
    	// use original size of bitmap to frame size (if use KEEP_ORIGINAL_DIMENSION)
    	
    	int wantedFrameWidth, wantedFrameHeight;
    	
    	
    	if( frameWidth == KEEP_ORIGINAL_DIMENSION)
    		wantedFrameWidth = originalBitmap.getWidth();
    	else
    		wantedFrameWidth = frameWidth;
    	
    	if( frameHeight == KEEP_ORIGINAL_DIMENSION)
    		wantedFrameHeight = originalBitmap.getHeight();
    	else
    		wantedFrameHeight = frameHeight;
    	
    	
    	if( (rotationInDegree == 90) || (rotationInDegree == 270) ){
	    	originalWidth = originalBitmap.getHeight();
	    	originalHeight = originalBitmap.getWidth();
    	}else{
    		originalWidth = originalBitmap.getWidth();
	    	originalHeight = originalBitmap.getHeight();
    	}
    	
    	
    	Log.i(TAG + " FILL frame", "Original width: " + Integer.toString(originalWidth));
    	Log.i(TAG + " FILL frame", "Original height: " + Integer.toString(originalHeight));
    	
    	
    	// calculate the resize ratio
    	
    	float widthRatio = (float) wantedFrameWidth / originalWidth;
    	float heightRatio = (float) wantedFrameHeight / originalHeight;
    	
    	Log.i(TAG + " FILL frame", "Width ratio: " + Float.toString(widthRatio));
    	Log.i(TAG + " FILL frame", "Height ratio: " + Float.toString(heightRatio));
    	
    	
    	
    	float biggerRatio;
    	
    	if(widthRatio > heightRatio)
    		biggerRatio = widthRatio;
    	else
    		biggerRatio = heightRatio;
    	
    	Log.i(TAG + " FILL frame", "Bigger ratio: " + Float.toString(biggerRatio));
    	
    	
    	Log.i(TAG + " FILL frame", "Resized size: " + Integer.toString((int) (originalWidth*biggerRatio)) + "x" + Integer.toString((int) (originalHeight*biggerRatio)));

    	
    	
    	int x = 0, y = 0;
    	
    	switch (cuttingMode){
    		case CUT_CENTER_VERTICAL:{
    			if( (originalWidth*biggerRatio) > frameWidth )
    				x = (int) (( (originalWidth*biggerRatio) - frameWidth ) / 2);
    			else
    				x = 0;
    			
    			break;
    		}
    		
    		case CUT_CENTER_HORIZONTAL:{
    			if((originalHeight*biggerRatio) > frameHeight)
    				y = (int)( ((originalHeight*biggerRatio) - frameHeight) /2 );
    			else
    				y = 0;
    			
    			break;
    		}
    		
    		case CUT_CENTER:{
    			
    			if( (originalWidth*biggerRatio) > frameWidth )
    				x = (int) (( (originalWidth*biggerRatio) - frameWidth ) / 2);
    			else
    				x = 0;
    			
    			if((originalHeight*biggerRatio) > frameHeight)
    				y = (int)( ((originalHeight*biggerRatio) - frameHeight) /2 );
    			else
    				y = 0;
    			
    			break;
    		}
    		
    	}
    
    	return Bitmap.createBitmap(
						getResized(originalBitmap, (int) (originalWidth*biggerRatio) , (int) (originalHeight*biggerRatio), rotationInDegree, directionOfRotation), 
						x,
						y, 
						frameWidth, 
						frameHeight
					);
    	   	
    }

    
    /**
	 * Resize/Scale the given bitmap to fill the specified frame and rotate it with given degrees. Note: if rotationDegrees == 90 or 180 then "fit to frame" calculation will use the rotated dimensions of bitmap 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param cuttingMode means will be cut off from the projecting parts
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap (Note: default rotation: CLOCKWISE)
	 * @return the result bitmap what fills the specified frame and rotated with given degrees
	 */
    public static Bitmap getResizedToFillFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, int cuttingMode, float rotationInDegree){
    	return getResizedToFillFrame(originalBitmap, frameWidth, frameHeight, cuttingMode, rotationInDegree, ROTATION_CLOCKWISE); 
    }
    
    
    /**
	 * Resize/Scale the given bitmap to fill the specified frame. 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param cuttingMode means will be cut off from the projecting parts
	 * @return the result bitmap what fills the specified frame.
	 */
    public static Bitmap getResizedToFillFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, int cuttingMode){
    	return getResizedToFillFrame(originalBitmap, frameWidth, frameHeight, cuttingMode, 0, ROTATION_CLOCKWISE);
    }
    
    
    /**
	 * Resize/Scale the given bitmap to fill the specified frame. Note: To cut off the projecting area will use the DEFAULT_CUTTING_MODE. 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @return the result bitmap what fills the specified frame.
	 */
    public static Bitmap getResizedToFillFrame(Bitmap originalBitmap, int frameWidth, int frameHeight){
    	return getResizedToFillFrame(originalBitmap, frameWidth, frameHeight, DEFATULT_CUTTING_MODE);
    }
    
    
    // Returns BitmapDrawable
    
    /**
	 * Resize/Scale the given bitmap to fill the specified frame and rotate it with given degrees to given direction and "convert" the result to BitmapDrawable. Note: if rotationDegrees == 90 or 180 then "fit to frame" calculation will use the rotated dimensions of bitmap 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param cuttingMode means will be cut off from the projecting parts
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap
	 * @param directionOfRotation can be clockwise (BitmapResizer.ROTATION_CLOCKWISE) or counterclockwise (BitmapResizer.ROTATION_COUNTER_CLOCKWISE) its means the direction of rotation
	 * @return the result bitmapDrawable what fills the specified frame and rotated (to the given direction) with given degrees
	 */
    public static BitmapDrawable getBitmapDrawableResizedToFillFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, int cuttingMode, float rotationInDegree, int directionOfRotation){
    	return new BitmapDrawable( getResizedToFillFrame(originalBitmap, frameWidth, frameHeight, cuttingMode, rotationInDegree, directionOfRotation) );
    }
    
    
    /**
	 * Resize/Scale the given bitmap to fill the specified frame and rotate it with given degrees and "convert" the result to BitmapDrawable. Note: if rotationDegrees == 90 or 180 then "fit to frame" calculation will use the rotated dimensions of bitmap 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param cuttingMode means will be cut off from the projecting parts
	 * @param rotationInDegree the rotation rate of the result bitmap compared to the original bitmap (Note: default rotation: CLOCKWISE)
	 * @return the result bitmapDrawable what fills the specified frame and rotated with given degrees
	 */
    public static BitmapDrawable getBitmapDrawableResizedToFillFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, int cuttingMode, float rotationInDegree){
    	return new BitmapDrawable( getResizedToFillFrame(originalBitmap, frameWidth, frameHeight, cuttingMode, rotationInDegree, ROTATION_CLOCKWISE) );
    }
  
    
    /**
	 * Resize/Scale the given bitmap to fill the specified frame and "convert" the result to BitmapDrawable. 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @param cuttingMode means will be cut off from the projecting parts
	 * @return the result bitmapDrawable what fills the specified frame.
	 */
    public static BitmapDrawable getBitmapDrawableResizedToFillFrame(Bitmap originalBitmap, int frameWidth, int frameHeight, int cuttingMode){
    	return new BitmapDrawable( getResizedToFillFrame(originalBitmap, frameWidth, frameHeight,  cuttingMode, 0, ROTATION_CLOCKWISE) );
    }

    
    /**
	 * Resize/Scale the given bitmap to fill the specified frame and "convert" the result to BitmapDrawable. Note: To cut off the projecting area will use the DEFAULT_CUTTING_MODE. 
	 * @param originalBitmap the bitmap what you want to resize.
	 * @param frameWidth the width of the frame into which you want to fit in the result
	 * @param frameHeight the height of the frame into which you want to fit in the result
	 * @return the result bitmapDrawable what fills the specified frame.
	 */
    public static BitmapDrawable getBitmapDrawableResizedToFillFrame(Bitmap originalBitmap, int frameWidth, int frameHeight){
    	return getBitmapDrawableResizedToFillFrame(originalBitmap, frameWidth, frameHeight, DEFATULT_CUTTING_MODE);
    }

    
}

