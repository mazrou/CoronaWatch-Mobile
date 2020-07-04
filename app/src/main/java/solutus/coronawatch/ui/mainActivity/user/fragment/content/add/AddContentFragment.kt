package solutus.coronawatch.ui.mainActivity.user.fragment.content.add

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.add_content_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import solutus.coronawatch.data.entity.AppUser
import solutus.coronawatch.data.entity.ProgressRequestBody
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.implementation.ContentRepository
import solutus.coronawatch.ui.mainActivity.MainActivity
import solutus.coronawatch.utilities.RealPathUtil
import java.io.File


class AddContentFragment : Fragment(){

    companion object {
        const val REQUEST_CODE_PICK_VIDEO_CAMERA = 101
        const val REQUEST_CODE_PICK_VIDEO_GALLERY = 102
        fun newInstance() =
            AddContentFragment()
    }
    private lateinit var activity : MainActivity
    private lateinit var token: String
    private lateinit var user: AppUser
    private val contentRepository =
        ContentRepository(
            ContentApi.invoke()
        )
    var progressdialog: ProgressDialog? = null
    private var selectedVideoUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        token = "token "+activity.token
        user = activity.user
        upload.setOnClickListener {
            showPictureDialog()
        }

        share_button?.setOnClickListener {
            if(video_view.duration == -1){//VideoView ne contient pas un video
                Toast.makeText(activity, "يجب اضافة فيديو", Toast.LENGTH_SHORT).show()

            }else{
               if(title?.text.toString() ==""||description?.text.toString() ==""){// un des champs est vide
                   Toast.makeText(activity, "يجب ملأ كل الحقول", Toast.LENGTH_SHORT).show()
               }else{
                   uploadVideo(this.user)
               }
            }
        }
    }
    private fun showPictureDialog() {
        val pictureDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(activity,
            AlertDialog.THEME_HOLO_LIGHT)
        val pictureDialogItems = arrayOf(
            "تحميل فيديو",
            "تصوير فيديو"
        )
        pictureDialog.setItems(pictureDialogItems,
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    0 -> chooseVideoFromGallary()
                    1 -> takeVideoFromCamera()
                }
            })
        val alertDialog : AlertDialog = pictureDialog.create()
        alertDialog.setOnShowListener(DialogInterface.OnShowListener() {

            alertDialog.window?.decorView?.layoutDirection = View.LAYOUT_DIRECTION_RTL

        })
        alertDialog.show()
    }
    private fun chooseVideoFromGallary() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "video/*"
            startActivityForResult(it, REQUEST_CODE_PICK_VIDEO_GALLERY)
        }
    }

    private fun takeVideoFromCamera() {
        val intent = Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA)
        startActivityForResult(intent, REQUEST_CODE_PICK_VIDEO_CAMERA)
    }


    private fun uploadVideo(user : AppUser) {//à changer
        // à coder ulterieurement
        //CreateProgressDialog
        video_view.visibility = View.GONE
        progressdialog = ProgressDialog(activity);
        progressdialog?.setIndeterminate(false);
        progressdialog?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressdialog?.setCancelable(true);
        progressdialog?.setMax(100);
        progressdialog?.show();
        //A changer
        progressdialog?.progress = 50
        progressdialog?.progress =100

        //upload

        val realPath :String? = RealPathUtil.getRealPath(context as Context ,selectedVideoUri as Uri)
        val originalFile : File = File(realPath!!)
        val str = context!!.contentResolver?.getType(selectedVideoUri as Uri) as String
        val file : RequestBody = RequestBody.create(str.toMediaTypeOrNull(),originalFile)
       // val fileBody = ProgressRequestBody(originalFile, this)

        val video : MultipartBody.Part = MultipartBody.Part.createFormData("file",originalFile.name,file )
        CoroutineScope(IO).launch {
            contentRepository.postVideo(
                token,
                title.text.toString(),
                description.text.toString(),
                video
            )

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_PICK_VIDEO_GALLERY) {
                selectedVideoUri = data?.data
                video_view.setVideoURI(selectedVideoUri);
                video_view.visibility = View.VISIBLE
                video_view.seekTo(1);

            }
            if (requestCode == REQUEST_CODE_PICK_VIDEO_CAMERA) {
                selectedVideoUri = data?.data
                video_view.setVideoURI(selectedVideoUri);
                video_view.visibility = View.VISIBLE
                video_view.seekTo(1);            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }


}
