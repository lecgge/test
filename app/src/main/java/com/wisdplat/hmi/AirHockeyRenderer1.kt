package com.wisdplat.hmi


import Point
import android.content.Context
import android.opengl.GLES20
import android.opengl.Matrix
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi


import androidx.compose.ui.graphics.Color
import com.wisdplat.hmi.base.BaseRenderer
import com.wisdplat.hmi.objects.Line
import com.wisdplat.hmi.objects.Line1
import com.wisdplat.hmi.objects.Table
import com.wisdplat.hmi.programs.TextureShaderProgram
import com.wisdplat.hmi.util.loadTexture
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class AirHockeyRenderer1(private val context: Context) : BaseRenderer(context) {

    lateinit var dline: Line1
    lateinit var dline1:Line1
    lateinit var dline2:Line1
    private var texture: Int = 0
    lateinit var table: Table
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        super.onSurfaceCreated(gl, config)
        table= Table()

        dline= Line1(1,App.context,Point(0f, 0.3f, 0F),)
        dline1= Line1(1,App.context,Point(0.9f, 0.3f, 0F))
        dline2= Line1(1,App.context,Point(-0.9f, 0.3f, 0F))

        textureShaderProgram= TextureShaderProgram(context)
          texture=context.loadTexture(R.drawable.table)

//        modelMap.forEach {
//            it.value.texture = context.loadTexture(R.drawable.car)
//        }

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onDrawFrame(gl: GL10?) {
//        addTextureModel(Table(1001, context, R.drawable.table, Point(0f, 0f, 0f)))

        super.onDrawFrame(gl)


//        positionTableInScene()
//        textureShaderProgram.useProgram()
//        textureShaderProgram.setUniforms(modelViewProjectionMatrix, texture)
//        table.bindData(textureShaderProgram)
//        table.draw()
//        drawTextureModel(dline)
//        drawTextureModel(dline1)
//        drawTextureModel(dline2)


        modelMap.forEach { id, model ->
            drawTextureModel(model)
        }

    }

    private fun positionTableInScene() {
        Matrix.setIdentityM(modelMatrix, 0)
        // 这张桌子是用X、Y坐标定义的，所以我们把它旋转90度，使它平放在xoz平面上
        Matrix.rotateM(modelMatrix, 0, -45f, 1f, 0f, 0f)
        Matrix.multiplyMM(
            modelViewProjectionMatrix, 0, viewProjectionMatrix,
            0, modelMatrix, 0
        )//viewProjectionMatrix和modelMatrix相乘，并将结果存储在modelViewProjectionMatrix中
    }
    override fun change(y: Int) {

        dline.position = Point(dline.position.x,dline.position.y-y*0.0002f,dline.position.z)
        dline1.position = Point(dline1.position.x,dline1.position.y-y*0.0002f,dline1.position.z)
        dline2.position = Point(dline2.position.x,dline2.position.y-y*0.0002f,dline2.position.z)

        if (dline.position.y < -0.25f) {
            dline.position.y = 0.25f
        }
        if (dline1.position.y < -0.25f) {
            dline1.position.y = 0.25f
        }
        if (dline2.position.y < -0.25f) {
            dline2.position.y = 0.25f
        }

    }

}