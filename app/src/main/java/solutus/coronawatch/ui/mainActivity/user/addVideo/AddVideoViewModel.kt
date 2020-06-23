package solutus.coronawatch.ui.mainActivity.user.addVideo

import androidx.lifecycle.ViewModel

class AddVideoViewModel : ViewModel() {
    var videoPath : String? = null

    fun uploadVideo(){
        /*CoroutineScope(IO).launch {
            try {
                contentRepository.postVideo(
                    token,
                    title.text.toString(),
                    description.text.toString(),
                    selectedVideoUri as Uri,
                    context as Context
                )
            } catch (e :GetDataFromApiException){
                Toast.makeText(requireContext() , "لم يتم ارسال الفيدو بنجاح",Toast.LENGTH_SHORT).show()
            }catch (e :Exception){
                Toast.makeText(requireContext() , "لم يتم ارسال الفيدو بنجاح",Toast.LENGTH_SHORT).show()
            }

        }*/
    }
}