package com.jacobzipper.lockscreenroulette;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TetrisView extends SurfaceView implements SurfaceHolder.Callback {
    private Piece piece;

    SurfaceHolder mSurfaceHolder;
    DrawingThread mThread;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    Paint mPaint;

    public TetrisView(Context pContext) {
        super(pContext);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mThread = new DrawingThread();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas pCanvas) {
        pCanvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLACK);

        int pieceSize;
        if(getHeight() / 20 > getWidth() / 10) {
            pieceSize = getWidth() / 10;
        } else {
            pieceSize = getHeight() / 20;
        }

        // Draw grid

        // Horizontal grid
        for(int i = 0; i != 20; i++) {
            pCanvas.drawLine(0, getHeight() - (i * pieceSize), 10 * pieceSize, getHeight() - (i * pieceSize), mPaint);
        }

        // Vertical grid
        for(int i = 0; i != 11; i++) {
            pCanvas.drawLine(pieceSize * i, 0, pieceSize * i, getHeight(), mPaint);
        }


        // Draw game board

        for(int i = 0; i != 10; i++) {
            for(int y = 0; y != 22; y++) {
                if(this.piece.getGameBoard()[i][y] != 0) {
                    pCanvas.drawRect(new RectF(pieceSize * i, (getHeight() - pieceSize) - (pieceSize * y), (pieceSize * i) + pieceSize, ((getHeight() - pieceSize) - (pieceSize * y)) + pieceSize), mPaint);
                }
            }
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder pHolder, int pFormat, int pWidth, int pHeight) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        mThread.keepDrawing = true;
        mThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder pHolder) {
        mThread.keepDrawing = false;
        boolean retry = true;
        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) {}
        }

    }

    private class DrawingThread extends Thread {
        boolean keepDrawing = true;

        @Override
        public void run() {
            Canvas canvas;
            while (keepDrawing) {
                canvas = null;

                try {
                    canvas = mSurfaceHolder.lockCanvas();
                    synchronized (mSurfaceHolder) {
                        onDraw(canvas);
                    }
                } finally {
                    if (canvas != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }

                // Pour dessiner à 50 fps
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
            }
        }
    }
}