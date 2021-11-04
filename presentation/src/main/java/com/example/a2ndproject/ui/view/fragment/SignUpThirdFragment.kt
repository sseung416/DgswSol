package com.example.a2ndproject.ui.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpThirdFragmentBinding
import com.example.a2ndproject.di.application.MyHiltApplication_HiltComponents
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.view.utils.MessageUtil
import com.example.a2ndproject.ui.view.utils.Preference.token
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.util.jar.Manifest

@AndroidEntryPoint
class SignUpThirdFragment : BaseFragment<SignUpThirdFragmentBinding>() {

    private val viewModel: SignUpViewModel by activityViewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun getLayoutResId(): Int =
        R.layout.sign_up_third_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK && it.data?.data != null) {
                try {
                    val imgUri = it.data?.data
                    viewModel.profile.value = imgUri

                    var bitmap: Bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        val source = ImageDecoder.createSource(requireActivity().contentResolver, imgUri!!)
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imgUri)
                    }

                    binding.ivProfileSignUpThird.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        binding.ivProfileSignUpThird.setOnClickListener {
            getImage()
        }
    }

    private fun getImage() {
        val readStorage = android.Manifest.permission.READ_EXTERNAL_STORAGE
        val permission = ActivityCompat.checkSelfPermission(requireActivity(), readStorage)

        if (permission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(readStorage), 1)
        }

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        resultLauncher.launch(Intent.createChooser(intent, "사진을 선택해주세요."))
    }

    private fun observe() = with(viewModel) {

    }
}