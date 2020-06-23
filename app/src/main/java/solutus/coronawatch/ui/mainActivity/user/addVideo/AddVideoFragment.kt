package solutus.coronawatch.ui.mainActivity.user.addVideo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.add_video_fragment.*
import solutus.coronawatch.data.entity.AppUser
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.ui.mainActivity.MainActivity


class AddVideoFragment : Fragment() {

    companion object {
        const val REQUEST_CODE_PICK_VIDEO_CAMERA = 101
        const val REQUEST_CODE_PICK_VIDEO_GALLERY = 102
        fun newInstance() =
            AddVideoFragment()
    }

    private lateinit var viewModel: AddVideoViewModel
    private lateinit var activity : MainActivity
    private lateinit var token: String
    private lateinit var user: AppUser
    private val contentRepository =
        ContentRepository(
            ContentApi.invoke()
        )
    private lateinit var mediaController: MediaController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_video_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set the view model
        viewModel = ViewModelProviders.of(this).get(AddVideoViewModel::class.java)

        token = "token "+activity.token
        user = activity.user!!

        mediaController = MediaController(this.context)
        mediaController.setAnchorView(video_view)
        video_view.setMediaController(mediaController)

        if (viewModel.videoPath != null) {
            setVideo()
        }

        //use on first time
        frame_view.setOnClickListener {
            showDialog()
        }
        //use to replace the video exist
        replace_video_button.setOnClickListener {
            showDialog()
        }

        //report case on button click
        share_button.setOnClickListener {
            shareVideo()
        }


    }

    private fun showDialog() {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custome_dialoge_video)
        val firstChoice = dialog.findViewById(R.id.first_choice_layout) as LinearLayout
        val secondChoice = dialog.findViewById(R.id.second_choice_layout) as LinearLayout
        firstChoice.setOnClickListener {
            chooseVideoFromGallery()
            dialog.dismiss()
        }
        secondChoice.setOnClickListener {
            takeVideoFromCamera()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun chooseVideoFromGallery() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "video/*"
            startActivityForResult(
                it,
                REQUEST_CODE_PICK_VIDEO_GALLERY
            )
        }
    }

    private fun takeVideoFromCamera() {
        val intent = Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA)
        startActivityForResult(
            intent,
            REQUEST_CODE_PICK_VIDEO_CAMERA
        )
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_PICK_VIDEO_GALLERY || requestCode == REQUEST_CODE_PICK_VIDEO_CAMERA) {
                val selectedVideoUri = data?.data
                viewModel.videoPath = getRealPathFromURI(selectedVideoUri)
                setVideo()
            }
        }
    }

    private fun shareVideo() {
        if (viewModel.videoPath == null) {
            Toast.makeText(activity, "يجب اضافة فيديو", Toast.LENGTH_SHORT).show()
        } else {
            if (description_edit.text.toString().trim().isEmpty()) {
                Toast.makeText(activity, "اضف وصفا", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.uploadVideo()
                frame_view.isClickable = true
                add_video_layout.visibility = View.VISIBLE
                video_view.visibility = View.GONE
                mediaController.visibility = View.GONE
                replace_video_frame.visibility = View.GONE
                //reset the photo path
                viewModel.videoPath = null
                //empty the description edit text
                description_edit.text.clear()
                description_edit.isCursorVisible = false
                //empty the title edit text
                title_edit.text.clear()
                title_edit.isCursorVisible = false
            }
        }
    }

    private fun setVideo() {
        add_video_layout.visibility = View.GONE
        video_view.visibility = View.VISIBLE
        frame_view.isClickable = false
        replace_video_frame.visibility = View.VISIBLE
        mediaController.visibility = View.VISIBLE
        video_view.setVideoPath(viewModel.videoPath)
        video_view.seekTo(1)
    }

    @SuppressLint("Recycle")
    private fun getRealPathFromURI(uri: Uri?): String? {
        val contentResolver: ContentResolver = requireActivity().contentResolver!!
        val projection =
            arrayOf(MediaStore.Video.Media.DATA)
        val cursor: Cursor = contentResolver.query(uri!!, projection, null, null, null)!!
        val columnIndex: Int = cursor
            .getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

}
