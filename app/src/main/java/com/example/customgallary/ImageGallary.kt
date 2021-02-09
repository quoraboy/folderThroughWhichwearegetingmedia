package com.example.customgallary

import android.content.Context
import android.database.Cursor
import android.graphics.Camera
import android.net.Uri
import android.provider.MediaStore

class ImageGallary {
    fun listofImage(context: Context): ArrayList<String> {
        var HashSetofallimageFolder = HashSet<String>()
        var listofallimage = ArrayList<String>()
        lateinit var uri: Uri
        var cursor: Cursor? = null
        var coloum_index_data: Int
        var coloum_index_folder_name: Int
        lateinit var AbsolutePathofImage: String
        lateinit var AbsolutePathofImageFolder: String
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var projection =
                arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)//projection will decide which columns to return
        //MediaStore.MediaColumns.DATA---> this columns contains path of images
        //MediaStore.Images.Media.BUCKET_DISPLAY_NAME ---> this columns contains folder name containing images
        var orderBy: String = MediaStore.Video.Media.DATE_TAKEN
        cursor = context.contentResolver.query(uri, projection, null, null, orderBy + " DESC")
        coloum_index_data = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)!!//try catch todo

        //getfoldername
        coloum_index_folder_name = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor?.moveToNext()) {
            AbsolutePathofImageFolder = cursor?.getString(coloum_index_folder_name)
            //AbsolutePathofImage=cursor?.getString(coloum_index_data)
            //listofallimage.add(AbsolutePathofImage)
            HashSetofallimageFolder.add(AbsolutePathofImageFolder)

        }

        return ArrayList(HashSetofallimageFolder);
    }
}