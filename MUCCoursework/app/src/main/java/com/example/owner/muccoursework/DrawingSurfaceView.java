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
        drawingThread.setRunning(true);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        drawingThread.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
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
