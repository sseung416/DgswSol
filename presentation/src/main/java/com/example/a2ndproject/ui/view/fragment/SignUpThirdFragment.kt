package com.example.a2ndproject.ui.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import com.example.a2ndproject.R
import com.example.a2ndproject.databinding.SignUpThirdFragmentBinding
import com.example.a2ndproject.ui.view.activity.MainActivity
import com.example.a2ndproject.ui.view.base.BaseFragment
import com.example.a2ndproject.ui.viewmodel.fragment.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

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

        observe()

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
        isSuccessPostQuickSignUp.observe(viewLifecycleOwner) {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
    }
}