package com.example.owner.muccoursework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by owner on 18/12/2014.
 */
public class DrawingThread extends Thread {
    private int canvasWidth;
    private int canvasHeight;
    private float xPos = 0.0f;
    private float yPos = 0.0f;
    private int i;

    private Calendar cToday = new GregorianCalendar();
    private int cCurrentDay = cToday.get(Calendar.DAY_OF_MONTH);
    private int cCurrentMonth = cToday.get(Calendar.MONTH);
    private int cCurrentYear = cToday.get(Calendar.YEAR);

    private float HalfAppletHeight;
    private float HalfAppletWidth;

    private int aCalcDays;
    private int cCalcDays;
    private int dDate;

    private boolean first = true;
    private boolean run = false;

    private SurfaceHolder drawingSurface;
    private Paint paintArea;
    private DrawingSurfaceView drawSurface;

    public DrawingThread(SurfaceHolder surfaceHolder, DrawingSurfaceView bioSurfV) {
        this.drawingSurface = surfaceHolder;
        this.drawSurface = bioSurfV;
        paintArea = new Paint();
        cCalcDays = calcDays(cCurrentDay, cCurrentMonth + 1, cCurrentYear);
    }

    public void doStart() {
        synchronized (drawingSurface) {
            int aDays = 16;
            int aMonth = 3;
            int aYear = 2014;
            int aCalcDays = calcDays(aDays, aMonth, aYear);
            dDate = cCalcDays - aCalcDays;
            first = false;

        }
    }

    public void run() {
        while (run) {
            Canvas c = null;
            try {
                c = drawingSurface.lockCanvas(null);
                synchronized (drawingSurface) {
                    svDraw(c);
                }
            } finally {
                if (c != null) {
                    drawingSurface.unlockCanvasAndPost(c);
                }
            }
        }
    }

    public void setRunning(boolean b) {
        run = b;
    }

    public void setSurfaceSize(int width, int height) {
        synchronized (drawingSurface) {
            canvasWidth = width;
            canvasHeight = height;
            HalfAppletHeight = canvasHeight / 2;
            HalfAppletWidth = canvasWidth / 32;
            doStart();
        }
    }


    private void svDraw(Canvas canvas) {
        if (run) {
            canvas.save();
            canvas.restore();
            canvas.drawColor(Color.WHITE);
            paintArea.setStyle(Paint.Style.FILL);
            drawAxes(canvas);
            paintArea.setColor(Color.RED);
            drawWave(canvas, 23);
            paintArea.setColor(Color.GREEN);
            drawWave(canvas, 28);
            paintArea.setColor(Color.BLUE);
            drawWave(canvas, 33);
        }
    }

    private int calcDays(int cdDaysIn, int cdMonthIn, int cdYearIn) {
        int iNoLeapYears, iNoYears, iNoYearsAsDays, iCurrentDays, iNumDays, iDaysSince;
        int aTotMonth[] = {31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
        iNoYears = cdYearIn - 1900;
        iNoLeapYears = (iNoYears / 4);
        iNumDays = aTotMonth[(cdMonthIn - 1)];
        iNoYearsAsDays = iNoYears * 365;
        return iDaysSince = iNoYearsAsDays + iNumDays + cdDaysIn + iNoLeapYears;
    }

    public void drawWave(Canvas theCanvas, int period) {
        float xPosOld = 0.0f;
        float yPosOld = 0.0f;
        int dStart = -15;
        int sDate = 0;
        int tDate = 0;

        sDate = dDate + dStart;

        for (i = 0; i <= 30; i++) {
            xPos = i * HalfAppletWidth;

            tDate = sDate + i;
            yPos = (float) (-100.0f * (Math.sin((tDate % period) * 2 * Math.PI / period)));

            if (i == 0)
                paintArea.setStyle(Paint.Style.FILL);
            else
                theCanvas.drawLine(xPosOld, (yPosOld + HalfAppletHeight), xPos, (yPos + HalfAppletHeight), paintArea);
            xPosOld = xPos;
            yPosOld = yPos;
        }
    }

    public void drawAxes(Canvas theCanvas) {
        paintArea.setColor(Color.BLACK);
        theCanvas.drawLine(0, HalfAppletHeight, 30 * HalfAppletWidth, HalfAppletHeight, paintArea); // Horizontal X Axes
        theCanvas.drawLine(15 * HalfAppletWidth, 0, 15 * HalfAppletWidth, canvasHeight, paintArea); // Vertical Y Axes
    }
}
