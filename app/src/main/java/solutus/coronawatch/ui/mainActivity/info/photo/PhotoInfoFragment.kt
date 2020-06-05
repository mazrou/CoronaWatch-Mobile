package solutus.coronawatch.ui.mainActivity.info.photo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.photo_info_fragment.*
import solutus.coronawatch.ui.mainActivity.info.InfoFragmentViewModel


class PhotoInfoFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoInfoFragment()
        const val REQUEST_CODE_PICK_IMAGE_GALLERY = 100
    }

    private lateinit var viewModel: PhotoInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //set view model
        viewModel = ViewModelProviders.of(this).get(PhotoInfoViewModel::class.java)

        if (viewModel.photoPath != null) {
            setPhoto()
        }

        //use on first time
        frame_view.setOnClickListener {
            chooseImageFromGallery()
        }
        //use to replace the photo exist
        replace_photo_button.setOnClickListener {
            chooseImageFromGallery()
        }
        //report case on button click
        report_button.setOnClickListener {
            reportCase()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_PICK_IMAGE_GALLERY) {
                val selectedImageUri = data?.data
                viewModel.photoPath = getRealPathFromURI(selectedImageUri)
                setPhoto()
            }
        }
    }

    private fun chooseImageFromGallery() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE_GALLERY)
        }
    }


    private fun reportCase() {
        if (viewModel.photoPath == null) {
            Toast.makeText(activity, "يجب اضافة صورة", Toast.LENGTH_SHORT).show()
        } else {
            if (description_edit.text.toString().trim().isEmpty()) {
                Toast.makeText(activity, "اضف وصفا", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.uploadCase(context, description_edit.text.toString())
                frame_view.isClickable = true
                add_photo_layout.visibility = View.VISIBLE
                photo_view.visibility = View.GONE
                replace_photo_frame.visibility = View.GONE
                //reset the photo path
                viewModel.photoPath = null
                //empty the edit text
                description_edit.text.clear()
                description_edit.isCursorVisible = false
            }
        }
    }

    private fun setPhoto() {
        add_photo_layout.visibility = View.GONE
        photo_view.visibility = View.VISIBLE
        frame_view.isClickable = false
        replace_photo_frame.visibility = View.VISIBLE
        photo_view.setImageBitmap(
            BitmapFactory.decodeFile(
                viewModel.photoPath
            )
        )
    }

    @SuppressLint("Recycle")
    private fun getRealPathFromURI(uri: Uri?): String? {
        val contentResolver: ContentResolver = requireActivity().contentResolver!!
        val projection =
            arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = contentResolver.query(uri!!, projection, null, null, null)!!
        val columnIndex: Int = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }
}
