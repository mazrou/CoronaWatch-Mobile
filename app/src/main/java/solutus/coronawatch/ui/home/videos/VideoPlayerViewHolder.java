package solutus.coronawatch.ui.home.videos;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.coronawatch_mobile.R;

import solutus.coronawatch.data.db.entity.Video;

public class VideoPlayerViewHolder extends RecyclerView.ViewHolder {

     FrameLayout media_container;
     TextView title;
     ImageView thumbnail, volumeControl;
     ProgressBar progressBar;
     View parent;
     RequestManager requestManager;

    public VideoPlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        media_container = itemView.findViewById(R.id.video_section);
        thumbnail = itemView.findViewById(R.id.video_thumbnil);
        title = itemView.findViewById(R.id.title);
        progressBar = itemView.findViewById(R.id.progressBar);
        volumeControl = itemView.findViewById(R.id.volumeControl);
    }

    public void onBind(Video video, RequestManager requestManager) {
        this.requestManager = requestManager;
        parent.setTag(this);
        title.setText(video.getTitle());
        this.requestManager
                .load(video.getThumbnail())
                .into(thumbnail);
    }

}
