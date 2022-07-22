package com.ymkiux.compose.common

import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView

/**
 * file picker
 * @param title the dialog title
 * @param dir the current directory to point to
 * @return the selected file
 */
fun fileChooserDialog(
    title: String?, dir: File? = null
): String? {
    val fileChooser = JFileChooser(FileSystemView.getFileSystemView())
    fileChooser.currentDirectory = dir
    fileChooser.dialogTitle = title
    fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
    fileChooser.isAcceptAllFileFilterUsed = true
    fileChooser.selectedFile = null
    val file = if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        fileChooser.selectedFile.toString()
    } else {
        null
    }
    return file
}