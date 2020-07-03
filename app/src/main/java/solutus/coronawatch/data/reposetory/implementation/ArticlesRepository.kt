package solutus.coronawatch.data.reposetory.implementation

import solutus.coronawatch.data.entity.AppUser
import solutus.coronawatch.data.entity.Article
import solutus.coronawatch.data.network.implementation.ContentApi
import solutus.coronawatch.data.reposetory.abstraction.SafeApiRequest

class ArticlesRepository(private val contentApi: ContentApi) : SafeApiRequest() {

    //pour tester
    fun getArticles():List<Article>{
        val user = AppUser(1,"Aymen","Ourdjini","04-12-1998","ga_ordjini@esi.dz","","",true,1,"https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png")
        val list = ArrayList<Article>()
        list.add(Article("1",user,"Corona Virus","https://www.google.com"))
        list.add(Article("1",user,"Corona Virus","https://vod-progressive.akamaized.net/exp=1592924857~acl=%2A%2F1025769516.mp4%2A~hmac=62bc9395e7260b8f34d65d3e3c2138017404b4692d4b9b8c515f997a691f63c8/vimeo-prod-skyfire-std-us/01/4995/10/274979394/1025769516.mp4?filename=6.4+Manually+Focusing+the+Camera+Preview.mp4"))
        return list
    }
}