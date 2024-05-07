package vn.root.app.pages.materialKit.chip

import android.os.Bundle
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ImageSpan
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import vn.root.app.R
import vn.root.app.databinding.FragmentChipBinding

class ChipFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentChipBinding
	private lateinit var navController: NavController
	private var chipLength = 0
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentChipBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.chip)
			setNavigationOnClickListener { navController.popBackStack() }
			menu.clear()
		}
		// TODO change approach -> Email Input Text with chips
		viewBinding.edtChip.doOnTextChanged { text, start, before, count ->
			if (chipLength > (text?.length ?: 0)) {
				chipLength = text?.length ?: 0
			}
			println("Text Changed: ${text.toString()} - $start - $before - $count")
		}
		viewBinding.edtChip.setOnEditorActionListener { textView, i, _ ->
			println("Editor Action: ${textView.text}")
			if (i == EditorInfo.IME_ACTION_DONE) {
				val chipDrawable =
					ChipDrawable.createFromResource(requireContext(), R.xml.item_chip)
				chipDrawable.text = textView.text.subSequence(chipLength, textView.text.length)
				chipDrawable.setBounds(
					0, 0, chipDrawable.intrinsicWidth, chipDrawable.intrinsicHeight
				)
				val span = ImageSpan(chipDrawable)
				val text = viewBinding.edtChip.text
				text.setSpan(span, chipLength, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
				chipLength = textView.length()
			}
			false
		}
	}
}