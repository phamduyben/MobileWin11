package com.example.bai3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvSongs;
    SongAdapter mSongAdapter;
    LinearLayoutManager linearLayoutManager;
    List<SongModel> mSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars =
                            insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );
        setupUI();
        setupProcess();
    }

    public void setupUI() {
        rvSongs = findViewById(R.id.rv_songs);
        addSong();
        mSongAdapter = new SongAdapter(mSongs, MainActivity.this);
        rvSongs.setAdapter(mSongAdapter);

        linearLayoutManager = new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSongs.setLayoutManager(linearLayoutManager);

        rvSongs.setAdapter(mSongAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );
        rvSongs.setLayoutManager(linearLayoutManager);
    }

    public void addSong() {
        mSongs = new ArrayList<>();
        mSongs.add(new SongModel(
                "60696",
                "NẾU EM CÒN TỒN TẠI",
                "Khi anh bắt đầu 1 tình yêu là lúc anh tự thay",
                "Trịnh Đình Quang"
        ));
        mSongs.add(new SongModel(
                "60701",
                "NGỐC",
                "Có rất nhiều câu chuyện em dấu riêng mình em biết",
                "Khắc Việt"
        ));
        mSongs.add(new SongModel(
                "60650",
                "HÃY TIN ANH LẦN NỮA",
                "Dẫu cho ta đã sai khi ở bên nhau cô yêu thương",
                "Thiên Dũng"
        ));
        mSongs.add(new SongModel(
                "60610",
                "CHUỖI NGÀY VẮNG EM",
                "Từ khi em bước ra đi cõi lòng anh ngập tràn bao",
                "Duy Cường"
        ));
        mSongs.add(new SongModel(
                "60656",
                "KHI NGƯỜI MÌNH YÊU KHÓC",
                "Nước mắt em đang rơi trên những ngón tay",
                "Phạm Mạnh Quỳnh"
        ));
        mSongs.add(new SongModel(
                "60685",
                "MỞ",
                "Anh mơ được gặp em anh mơ",
                "Trịnh Thăng Bình"
        ));
        mSongs.add(new SongModel(
                "60752",
                "TÌNH YÊU CHẤP VÁ",
                "Muốn đi xa nơi yêu thương mình từng có",
                "Mr.Siro"
        ));
        mSongs.add(new SongModel(
                "60608",
                "CHỜ NGÀY MƯA TAN",
                "1 ngày mưa và em khuất xa nơi anh",
                "Trung Đức"
        ));
        mSongs.add(new SongModel(
                "60603",
                "CÂU HỎI EM CHƯA TRẢ LỜI",
                "Cần nơi em 1 lời giải thích thật lòng",
                "Yuki Huy Nam"
        ));
        mSongs.add(new SongModel(
                "60720",
                "QUA ĐI LẶNG LẼ",
                "Đôi khi đến voiwsn hau yêu thương chả được lâu",
                "Phan Mạnh Quỳnh"
        ));
        mSongs.add(new SongModel(
                "60856",
                "QUÊN ANH LÀ ĐIỀU EM KHÔNG THỂ - REMIX",
                "Cần thêm bao lâu để em quên đi niềm đau",
                "Thiện Ngôn"
        ));
        mSongAdapter = new SongAdapter(mSongs, this);

    }

    public void setupProcess() {

    }
}