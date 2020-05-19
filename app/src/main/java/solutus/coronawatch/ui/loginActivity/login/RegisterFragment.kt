package solutus.coronawatch.ui.loginActivity.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.coronawatch_mobile.R
import kotlinx.android.synthetic.main.register_fragment.*
import kotlinx.android.synthetic.main.register_fragment.email
import kotlinx.android.synthetic.main.register_fragment.password
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import solutus.coronawatch.data.db.entity.AppUser
import solutus.coronawatch.data.network.implementation.UserApi
import solutus.coronawatch.data.reposetory.implementation.UserRepository
import solutus.coronawatch.ui.mainActivity.MainActivity

class RegisterFragment : Fragment() , KodeinAware {
    override val kodein: Kodein by  closestKodein()
    private val userRepository :UserRepository by instance()
    private var user : AppUser? = null
    private var emailPassword = HashMap<String,String>()
    private lateinit var token : String


    companion object {
        fun newInstance() = RegisterFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        login.setOnClickListener {
            MainActivity.replaceFragment(activity, R.id.login_fragment, LoginFragment())
        }

        register.setOnClickListener {
           if(agree.isChecked){
               if(email.text.toString()==""||first_name.text.toString()==""||second_name.text.toString()==""||password.text.toString()==""||confirm_password.text.toString()==""){
                   Toast.makeText(activity,"يجب ملأ كل الحقول",Toast.LENGTH_SHORT).show()
               }else{
                   //TODO:Registre un user
                   //val user = User()
                   val email = email.text.toString()
                   val fName = first_name.text.toString()
                   val lName = second_name.text.toString()
                   val firstPassword= password.text.toString()
                   val secondPassword = confirm_password.text.toString()
                   emailPassword["email"] = email
                   emailPassword["password"] = firstPassword
                   CoroutineScope(IO).launch {
                       println("////////////////////////")
                       user = userRepository.registerUser(email,fName,lName,firstPassword,secondPassword)
                       println("////////////////////////")
                       println(user)
                       token = userRepository.loginUser(emailPassword)!!.token
                       login(user!!,token)
                   }



               }

           }else{
               Toast.makeText(activity,"يجب ان توافق على شروط الاستخدام",Toast.LENGTH_SHORT).show()
           }
        }
        condition.setOnClickListener {
            //TODO: afficher les condition d'utlisation
        }
    }

    suspend fun login(user : AppUser, token : String){
        withContext(Dispatchers.Main){
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("token",token)
            intent.putExtra("user",user)
            startActivity(intent)
        }
    }

}
