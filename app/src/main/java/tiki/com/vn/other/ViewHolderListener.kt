package tiki.com.vn.other

interface ViewHolderListener<T> {

    fun itemClicked(data: T, positon: Int)
}