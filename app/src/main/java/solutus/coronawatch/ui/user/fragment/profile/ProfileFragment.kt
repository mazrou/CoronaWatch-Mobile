package solutus.coronawatch.ui.user.fragment.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.coronawatch_mobile.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_fragment.*
import solutus.coronawatch.service.DatePickerFragment
import solutus.coronawatch.ui.user.UserFragment
import java.util.*


class ProfileFragment : Fragment() {

    companion object {

        const val REQUEST_CODE_PICK_IMAGE_CAMERA = 101
        const val REQUEST_CODE_PICK_IMAGE_GALLERY = 102
        const val REQUEST_CODE_DATE_PICKER = 100
        fun newInstance() =
            ProfileFragment()
    }


    private lateinit var selectedDate: String
    private lateinit var dateOfBirthET: EditText
    private lateinit var userName :EditText
    private lateinit var name :EditText
    private lateinit var dateNaissance :EditText
    private lateinit var password :EditText
    private lateinit var password2 :EditText
    private lateinit var avatar:de.hdodenhof.circleimageview.CircleImageView
    private var selectedImageUri: Uri? = null
    private lateinit var photo: Bitmap
    private var buttonClicked=false
    private var imageSelected=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.profile_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //modifier l'icone de l'avatar à ic_plus
        avatar = activity?.findViewById<View>(R.id.profile_image) as de.hdodenhof.circleimageview.CircleImageView
        avatar.setOnClickListener{
            showPictureDialog()
        }
        //manipulation du Date Picker
        dateOfBirthET = view?.findViewById<EditText>(R.id.date_naissance)!!
        dateOfBirthET.inputType = InputType.TYPE_NULL
        dateOfBirthET.setOnClickListener {
            showDatePicker(this, REQUEST_CODE_DATE_PICKER)
        }
        //souvegarder l'état du profile
        val sauvegardBtn =  view?.findViewById<Button>(R.id.souvegarder_button)
        sauvegardBtn?.setOnClickListener{
            userName =  view?.findViewById<EditText>(R.id.user_name) as EditText
            name =  view?.findViewById<EditText>(R.id.name) as EditText
            dateNaissance =  view?.findViewById<EditText>(R.id.date_naissance) as EditText
            password =  view?.findViewById<EditText>(R.id.password) as EditText
            password2 =  view?.findViewById<EditText>(R.id.confirm_password) as EditText
            if(userName?.text.toString() =="" ||name?.text.toString() ==""||dateNaissance?.text.toString() ==""||password?.text.toString() ==""||password2?.text.toString() ==""){
                Toast.makeText(activity, "يجب ملأ كل الحقول", Toast.LENGTH_SHORT).show()

            }else{
                if(password?.text.toString() != password2?.text.toString()){
                    Toast.makeText(activity,"كلمتا المرور غير متطابقتين",Toast.LENGTH_SHORT).show()
                }else{
                    buttonClicked=true
                    uploadPhotoProfile()
                    souvegardeProfile()
                }
            }
        }
    }
    private fun showDatePicker(fragment:Fragment,requestCode: Int){
        val fm: FragmentManager = (activity as AppCompatActivity?)!!.supportFragmentManager
        //Change the language to Arabic
        val locale: Locale? = Locale("ar")
        Locale.setDefault(locale)
        val config: Configuration? = activity?.baseContext?.resources?.configuration
        config?.setLocale(locale)
        config?.let { it1 -> activity?.createConfigurationContext(it1) }
        val newFragment: AppCompatDialogFragment = DatePickerFragment()
        newFragment.setTargetFragment(fragment,requestCode)
        newFragment.show(fm, "datePicker")
    }
    private fun showPictureDialog() {
        val pictureDialog: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(activity,AlertDialog.THEME_HOLO_LIGHT)
        val pictureDialogItems = arrayOf(
            "تحميل صورة",
            "التقاط صورة"
        )
        pictureDialog.setItems(pictureDialogItems,
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    0 -> chooseImageFromGallary()
                    1 -> takeImageFromCamera()
                }
            })
        val alertDialog : AlertDialog = pictureDialog.create()
        alertDialog.setOnShowListener(DialogInterface.OnShowListener() {

                alertDialog.window?.decorView?.layoutDirection = View.LAYOUT_DIRECTION_RTL

        })
        alertDialog.show()
    }
    private fun chooseImageFromGallary() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE_GALLERY)
        }
    }

    private fun takeImageFromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE_CAMERA)
    }


    private fun uploadPhotoProfile() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }
    private fun souvegardeProfile(){//A changer
        Toast.makeText(activity, ""+ userName.text+" "+name.text+" "+dateNaissance.text+" "+password.text+" "+password2.text+" ", Toast.LENGTH_SHORT).show()
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_DATE_PICKER) {
                // get date from string
                selectedDate = data?.getStringExtra("selectedDate").toString()
                // set the value of the editText
                dateOfBirthET.setText(selectedDate)
            }
            if (requestCode == REQUEST_CODE_PICK_IMAGE_GALLERY) {
                imageSelected=true
                selectedImageUri = data?.data
                avatar.setImageURI(selectedImageUri)
            }
            if (requestCode == REQUEST_CODE_PICK_IMAGE_CAMERA) {
                imageSelected=true
                photo = data?.extras?.get("data") as Bitmap
                avatar.setImageBitmap(photo)
            }
        }
    }

    override fun onResume() {
        if(!imageSelected){
            avatar.setImageResource(R.mipmap.ic_plus)
            avatar.setCircleBackgroundColorResource(R.color.avatarBackground)
        }
        avatar.isClickable = true
        super.onResume()
    }
    override fun onPause() {
        if(!buttonClicked || !imageSelected){//si le button n'est pas cliqué
            //on revene à l'image d'origine
            Picasso.get().load(UserFragment.getPhotoProfile()).into(avatar)

        }
        avatar.isClickable = false
        super.onPause()
    }
}

