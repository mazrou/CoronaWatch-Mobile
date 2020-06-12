package solutus.coronawatch.ui.loginActivity.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.coronawatch_mobile.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import solutus.coronawatch.TokenApp
import solutus.coronawatch.data.entity.AppUser
import solutus.coronawatch.data.internal.GetDataFromApiException
import solutus.coronawatch.data.network.NetworkConnexion
import solutus.coronawatch.data.network.entity.Token
import solutus.coronawatch.data.network.implementation.UserApi
import solutus.coronawatch.data.reposetory.implementation.UserRepository
import solutus.coronawatch.ui.mainActivity.MainActivity


class LoginFragment : Fragment()  , KodeinAware{


    override val kodein by closestKodein()

    private val userRepository =
        UserRepository(UserApi.invoke())
    private var emailPassword = HashMap<String,String>()
    private lateinit var token : String
    private var user : AppUser? = null
    companion object {
        fun newInstance() = LoginFragment()
    }
    private val networkConnexion : NetworkConnexion by instance<NetworkConnexion>()
    val callbackManager : CallbackManager =  CallbackManager.Factory.create()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logout.setOnClickListener {
            val navController: NavController =
                Navigation.findNavController(requireActivity(), R.id.login_fragment)
            navController.navigate(R.id.to_register_fragment_action)
        }
        networkConnexion.observe(viewLifecycleOwner , Observer {isOnlise ->
            if(isOnlise) {
                error.visibility = View.GONE
                enter.isEnabled = true
                enter.setOnClickListener {
                    if (email.text.toString() == "" || password.text.toString() == "") {
                        Toast.makeText(activity, "يجب ملأ كل الحقول", Toast.LENGTH_SHORT).show()
                    } else {
                        //TODO :login
                        emailPassword["email"] = email.text.toString().trim()
                        emailPassword["password"] = password.text.toString().trim()
                        CoroutineScope(IO).launch {
                            apiRequest()
                        }
                    }
                }
                forgot_pw.setOnClickListener {
                        //TODO : forget password
                }
                 loginFromFacebook()

                facebook.setOnClickListener {
                    facebook()
                }
            }
            else{
                enter.isEnabled = false
                setErrorMessage("جهازك غير متصل بالانترنت")
            }
        })


    }

    private fun loginFromFacebook(){

    println("Hey I'm a facebook login")

        login_Facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                Toast.makeText(requireContext() ,  "User ID: " + loginResult.accessToken
                    .userId + "\n" + "Auth Token: " + loginResult.accessToken
                    .token , Toast.LENGTH_LONG).show()
            }

            override fun onCancel() {
                println("Hey I'm a facebook cancel")

            }

            override fun onError(e: FacebookException) {
                println("Hey I'm a facebook error")
            }
        })
    }
    private fun facebook(){

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("https://solutusprojet.herokuapp.com/social/login/facebook/"))
        startActivity(intent)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode , resultCode , data)

    }

    private suspend fun apiRequest() {
        try {
            this.token = userRepository.loginUser(this.emailPassword)!!.token
            this.user = getUser(this.token)
            TokenApp.token = Token("token $token")
            user?.let { showToken(it) }
        }catch (e : GetDataFromApiException){
            println("Network call exception ${e.message}")
            setErrorMessage("البريد أو كلمة السر خاطئة")
        }
    }

    private fun setErrorMessage(message : String ){
        CoroutineScope(Main).launch{
            error.text = message
            error.visibility = View.VISIBLE
        }
    }

    private suspend fun showToken(user: AppUser) {
        withContext(Dispatchers.Main){
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("token",token)
            intent.putExtra("user",user)
            startActivity(intent)
        }
    }

    private suspend fun getUser(token: String): AppUser? {
        val appUser =  userRepository.getAuthAppUser("token "+token)
        return appUser
    }

}
