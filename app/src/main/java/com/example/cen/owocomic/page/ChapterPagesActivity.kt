package com.example.cen.owocomic.page;

import android.os.Bundle
import com.example.cen.owocomic.R

import com.example.cen.owocomic.http.bean.ComicPageBean
import com.example.cen.owocomic.mvp.ChapterPagesPresenter;
import com.example.cen.owocomic.mvp.base.BaseMvpActivity;
import com.example.cen.owocomic.mvp.contract.ChapterPagesContract;
import kotlinx.android.synthetic.main.activity_comic_chapter_page.*

class ChapterPagesActivity : BaseMvpActivity<ChapterPagesPresenter>(), ChapterPagesContract.View {

    var name :String?= null;
    var chapter :String?= null;
    var mData :ArrayList<String> = ArrayList<String>();

    override fun getLayoutId(): Int {
        return R.layout.activity_comic_chapter_page
    }

    override fun generatePresenter(): ChapterPagesPresenter {
        return ChapterPagesPresenter(this)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError() {

    }

    override fun onCache(data: ComicPageBean?) {

    }

    override fun onSuccess(data: ComicPageBean?) {
        mData?.addAll(data!!.pages)
        vp_pages.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = intent.getStringExtra("name")
        chapter = intent.getStringExtra("chapter")
        mPresenter.fetchChapterPages(name,chapter)
        initView()
    }

    fun initView(){
        vp_pages.adapter = ComicPagerAdapter(this, mData)
    }

}
