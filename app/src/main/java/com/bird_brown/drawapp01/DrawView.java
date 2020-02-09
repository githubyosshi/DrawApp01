package com.bird_brown.drawapp01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

//DrawViewクラスに定義するメンバー変数
public class DrawView extends View {
    private float x;    //x座標
    private float y;    //y座標
    private Paint paint;    //Paint
    private Path path;  //Path
    private ArrayList<Path> pathList;   //Pathを格納するArrayList

    //３つのコンストラクタ定義
    public DrawView(Context context) {
        super(context);     //引数contextをスーパクラスのコンストラクタに渡す
        init();             //初期化処理用のinitメソッドを呼び出す
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //DrawViewクラスに定義するメソッド
    private void init() {
        pathList = new ArrayList<Path>();   //Path情報を格納する

        paint = new Paint();                //Paintクラスのオブジェクトを生成する
        paint.setAntiAlias(true);               //アンチエイリアスを設定する
        paint.setColor(Color.BLUE);             //色を青に設定
        paint.setStyle(Paint.Style.STROKE);     //図形の輪郭線のみを描写
        paint.setStrokeWidth(6.0F);             //線の太さを設定
        paint.setStrokeCap(Paint.Cap.ROUND);    //描画時の線の先端を丸くする
        paint.setStrokeJoin(Paint.Join.ROUND);  //描画時の線のつなぎ目を丸くする
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //pathがnullでなければ（描画処理があれば）、キャンバスに描画する
        if (path != null) {
            canvas.drawPath(path, paint);
        }
        //ArrayListに格納されているPath情報を順次キャンバスに描画する
        for (Path path : pathList) {
            canvas.drawPath(path, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {    //画面をタッチした時
        x = event.getX();   //x座標値を取得
        y = event.getY();   //y座標値を取得

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:   //画面をタッチした時
                path = new Path();  //Pathのオブジェクトを生成
                path.moveTo(x, y);  //線の始点を設定
                break;
            case MotionEvent.ACTION_MOVE:   //画面から移動した時
                path.lineTo(x, y); //線の終点を設定
                break;
            case MotionEvent.ACTION_UP:     //画面から離した時
                path.lineTo(x, y);  //線の終点を設定
                pathList.add(path); //ArrayListにPathの情報を追加
                break;
        }

        invalidate();   //再描画

        return true;
    }

}
