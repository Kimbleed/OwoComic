package com.example.cen.owocomic.page;

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cen.owocomic.DownloadManager
import com.example.cen.owocomic.R
import com.example.cen.owocomic.greendao.bean.Chapter
import com.example.cen.owocomic.http.HttpUrl
import com.example.cen.owocomic.http.bean.ComicChapterHttpBean
import com.example.cen.owocomic.mvp.ComicDetailInfoPresenter
import com.example.cen.owocomic.mvp.base.BaseMvpActivity
import com.example.cen.owocomic.mvp.contract.ComicChapterContract
import com.example.cen.owocomic.utils.BitmapUtil
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_comic_chapter.*
import kotlinx.android.synthetic.main.layout_item_chapter.view.*
import kotlinx.android.synthetic.main.layout_item_comic_face.*


class LoadChapterActivity : BaseMvpActivity<ComicDetailInfoPresenter>(), ComicChapterContract.View {

    var mComicName: String? = ""
    var mChapters: List<String> = ArrayList<String>();

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {
        refresh_layout.finishRefresh()
    }

    override fun onCache(data: ComicChapterHttpBean?) {
        refreshData(data, false)
    }

    override fun onSuccess(data: ComicChapterHttpBean?) {
        refreshData(data, true)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_load_chapter
    }

    override fun generatePresenter(): ComicDetailInfoPresenter {
        return ComicDetailInfoPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mComicName = intent.getStringExtra("comic")
        mPresenter.fetchComicDetailInfo(mComicName)
        initView()
        initRecyclerView()
    }

    private fun refreshData(data: ComicChapterHttpBean?, hideLoad: Boolean) {
        mChapters = data!!.chapters
        rv_chapter_list.adapter?.notifyDataSetChanged()

        if (hideLoad)
            refresh_layout.finishRefresh()

        ImageLoader.getInstance().loadImage(HttpUrl.baseUrl + "/" + data!!.faceUrl, object : ImageLoadingListener {
            override fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?) {

                iv_face.setImageBitmap(BitmapUtil.cut(loadedImage, 1F * iv_face.width / iv_face.height, BitmapUtil.CUT_FIT.START))
            }

            override fun onLoadingStarted(imageUri: String?, view: View?) {

            }

            override fun onLoadingCancelled(imageUri: String?, view: View?) {

            }

            override fun onLoadingFailed(imageUri: String?, view: View?, failReason: FailReason?) {

            }
        })
    }

    private fun initView() {
        tv_title.text = "下载章节"
        var bezierRadarHeader = BezierRadarHeader(this)
                .setEnableHorizontalDrag(true)
                .setAccentColorId(R.color.white)
                .setPrimaryColorId(R.color.comic_theme_color_light)
        refresh_layout.setRefreshHeader(bezierRadarHeader)
        refresh_layout.setOnRefreshListener(object : OnRefreshListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                mPresenter.fetchComicDetailInfo(mComicName)
            }
        })

    }

    private fun initRecyclerView() {
        rv_chapter_list.layoutManager = GridLayoutManager(this@LoadChapterActivity, 3)
        rv_chapter_list.adapter = ChapterRvAdapter()
    }

    inner class ChapterRvAdapter constructor() : RecyclerView.Adapter<ComicVH>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ComicVH {
            return ComicVH(LayoutInflater.from(this@LoadChapterActivity).inflate(R.layout.layout_item_chapter, p0, false))
        }

        override fun getItemCount(): Int {
            return this@LoadChapterActivity.mChapters.size;
        }

        override fun onBindViewHolder(vh: ComicVH, pos: Int) {
            vh.itemView.tv_name.text = this@LoadChapterActivity.mChapters.get(pos)
            vh.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    DownloadManager.instance().downloadChapter(Chapter(mComicName,mChapters.get(pos),0))
                }
            })
        }
    }

    class ComicVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}