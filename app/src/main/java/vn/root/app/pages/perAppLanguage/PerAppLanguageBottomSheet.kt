package vn.root.app.pages.perAppLanguage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import vn.root.app.databinding.LayoutBottomSheetPerAppLanguageBinding
import vn.root.domain.model.LanguageCodeEnum

class PerAppLanguageBottomSheet(private val onLangCodeChangeListener: (lang: LanguageCodeEnum) -> Unit) :
    BottomSheetDialogFragment() {

    companion object {
        const val TAG = "PerAppLanguageBottomSheet"
    }

    private lateinit var viewBinding: LayoutBottomSheetPerAppLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewBinding = LayoutBottomSheetPerAppLanguageBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        eventView()
    }

    private fun initView() {
        val langCode = AppCompatDelegate.getApplicationLocales().get(0)?.language
        when(langCode) {
            LanguageCodeEnum.EN.code -> viewBinding.radioEN.isChecked = true
            LanguageCodeEnum.VI.code -> viewBinding.radioVN.isChecked = true
        }
    }

    private fun eventView() {
        viewBinding.btnClosed.setOnClickListener {
            dismiss()
        }

        viewBinding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                viewBinding.radioEN.id -> onLangCodeChangeListener.invoke(LanguageCodeEnum.EN)

                viewBinding.radioVN.id -> onLangCodeChangeListener.invoke(LanguageCodeEnum.VI)
            }
            dismiss()
        }
    }
}