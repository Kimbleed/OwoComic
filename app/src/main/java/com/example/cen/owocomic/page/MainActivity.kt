package com.example.cen.owocomic.page

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cen.owocomic.R
import com.example.cen.owocomic.greendao.bean.Comic
import com.example.cen.owocomic.http.HttpUrl
import com.example.cen.owocomic.http.bean.ComicFaceHttpBean
import com.example.cen.owocomic.mvp.MainPresenter
import com.example.cen.owocomic.mvp.base.BaseMvpActivity
import com.example.cen.owocomic.mvp.contract.MainContract
import com.example.cen.owocomic.utils.BitmapUtil
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.header.FalsifyFooter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item_comic_face.*
import kotlinx.android.synthetic.main.layout_item_comic_face.view.*
import kotlinx.android.synthetic.main.layout_title.*
import kotlinx.android.synthetic.main.layout_title.tv_title


class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View<ComicFaceHttpBean> {

    var mComics: List<Comic> = ArrayList();

    override fun generatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {
        refresh_layout.finishRefresh()
    }

    override fun onCache(data: ComicFaceHttpBean?) {
        mComics = data!!.comics

        rv_comicList.adapter?.notifyDataSetChanged()
    }


    override fun onSuccess(data: ComicFaceHttpBean?) {
        mComics = data!!.comics

        rv_comicList.adapter?.notifyDataSetChanged()

        refresh_layout.finishRefresh()

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initRecyclerView()
        mPresenter.loadComicList()
    }

    fun initView() {
        tv_title.text = "漫画"

        var bezierRadarHeader = BezierRadarHeader(this)
                .setEnableHorizontalDrag(true)
                .setAccentColorId(R.color.white)
                .setPrimaryColorId(R.color.comic_theme_color)
        refresh_layout.setRefreshHeader(bezierRadarHeader)

        refresh_layout.setRefreshFooter(FalsifyFooter(this))

        refresh_layout.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                mPresenter.loadComicList()
            }
        })

    }

    fun initRecyclerView() {
        rv_comicList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_comicList.adapter = ComicRvAdapter()
    }

    inner class ComicRvAdapter constructor() : RecyclerView.Adapter<ComicVH>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ComicVH {
            return ComicVH(LayoutInflater.from(this@MainActivity).inflate(R.layout.layout_item_comic_face, p0, false))
        }

        override fun getItemCount(): Int {
            return this@MainActivity.mComics.size;
        }

        override fun onBindViewHolder(vh: ComicVH, pos: Int) {
            vh.itemView.tv_title.text = this@MainActivity.mComics.get(pos).name
            vh.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    var intent = Intent(this@MainActivity, ComicChapterActivity::class.java)
                    intent.putExtra("comic", mComics.get(pos).name)
                    startActivity(intent)
                }
            })

            ImageLoader.getInstance().loadImage(HttpUrl.baseUrl + "/" + mComics.get(pos).faceUrl,object: ImageLoadingListener {
                override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {

                    vh.itemView.iv_face?.setImageBitmap(BitmapUtil.cut(loadedImage,1F*vh.itemView.iv_face.width/vh.itemView.iv_face.height, BitmapUtil.CUT_FIT.START))
                }

                override fun onLoadingStarted(imageUri: String?, view: View?) {

                }

                override fun onLoadingCancelled(imageUri: String?, view: View?) {

                }

                override fun onLoadingFailed(imageUri: String?, view: View?, failReason: FailReason?) {
                    Log.i("onLoadFailed",imageUri)
                }
            })
        }
    }

    class ComicVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}
