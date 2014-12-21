package com.example.owner.muccoursework;

/**
 * Created by owner on 18/12/2014.
 */

import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    private SurfaceHolder CanvasSurface;
    DrawingThread drawingThread = null;

    public DrawingSurfaceView(Context context)
    {
        //assign proper surface holder to the instance of SurfaceHolder and link the new instance of DrawingSurfaceView
        //pass the holder and context into a new thread and set the focus to the instance of the class
        super(context);
        CanvasSurface = getHolder();
        CanvasSurface.addCallback(this);
        drawingThread = new DrawingThread(getHolder(), this);
        setFocusable(true);
    }

    public DrawingThread getThread()
    {
        return drawingThread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        //set thread to running and start it
        drawingThread.setRunning(true);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        //calls setSurfaceSize method to adapt to new size if the surface size is altered
        drawingThread.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        //stops thread from running, making sure no exceptions are created
        boolean retry = true;
        drawingThread.setRunning(false);
        while(retry)
        {
            try{
                drawingThread.join();
                retry = false;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
