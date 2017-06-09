package com.tiffany.com.filetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    private String fileName = "text.txt";
    private String message = "++追加了内容";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        Button writeBtn = (Button) findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
            }
        });
        Button readBtn = (Button) findViewById(R.id.readBtn);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(readFile());
            }
        });



    }



    /**
     * 读取文件
     *
     * @return 读取到的文件内容
     */
    private String readFile() {
        FileInputStream inputStream;
        byte[] buffer = null;
        try {
            inputStream = this.openFileInput(fileName);
            try {
                // 获取文件内容长度
                int fileLen = inputStream.available();
                // 读取内容到buffer
                buffer = new byte[fileLen];
                inputStream.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 返回文本信息
        if (buffer != null)
            return EncodingUtils.getString(buffer, "utf-8");
        else
            return "没有数据";

    }



    /**
     * 写出文件内容到文件
     */
    private void writeFile() {
        try {
            // 打开文件，该文件只能由调用该方法的应用程序访问
            // MODE_PRIVATE 该文件只能由调用该方法的应用程序访问
            // MODE_APPEND 如果文件已存在，就在结尾追加内容，而不是覆盖文件
            // MODE_WORLD_READABLE 赋予所有应用程序读权限
            // MODE_WORLD_WRITEABLE 赋予所有应用程序写权限
            FileOutputStream outStream = this.openFileOutput(fileName,
                    MODE_PRIVATE+MODE_APPEND);
            // 将文本转换为字节集
            byte[] data = message.getBytes();
            try {
                // 写出文件
                outStream.write(data);
                outStream.flush();
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
