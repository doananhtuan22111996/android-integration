package vn.root.app.pages.materialKit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import vn.root.app.R
import vn.root.app.databinding.FragmentMaterialKitBinding

class MaterialKitFragment : Fragment() {
	
	private lateinit var viewBinding: FragmentMaterialKitBinding
	private lateinit var navController: NavController
	private lateinit var adapter: MaterialKitAdapter
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		adapter = MaterialKitAdapter(
			kits = listOf(
				MaterialKit(
					id = 1,
					header = getString(R.string.typography),
					subHeader = getString(R.string.use_typography_to_present_your_design_and_content_as_clearly_and_efficiently_as_possible),
					action = { navController.navigate(R.id.action_materialKitFragment_to_typographyFragment) },
				), MaterialKit(
					id = 2,
					header = getString(R.string.button),
					subHeader = getString(R.string.buttons_help_people_take_actions_such_as_sending_an_email_sharing_a_document_or_liking_a_comment),
					action = { navController.navigate(R.id.action_materialKitFragment_to_buttonFragment) },
				), MaterialKit(
					id = 3,
					header = getString(R.string.card),
					subHeader = getString(R.string.cards_are_versatile_containers_holding_anything_from_images_to_headlines_supporting_text_buttons_lists_and_other_components),
					action = { navController.navigate(R.id.action_materialKitFragment_to_cardFragment) },
				), MaterialKit(
					id = 4,
					header = getString(R.string.top_tab_bars),
					subHeader = getString(R.string.top_app_bars_display_information_and_actions_at_the_top_of_a_screen_such_as_the_page_title_and_shortcuts_to_actions),
					action = { navController.navigate(R.id.action_materialKitFragment_to_topTabBarFragment) },
				), MaterialKit(
					id = 5,
					header = getString(R.string.bottom_app_bar),
					subHeader = getString(R.string.bottom_app_bars_display_navigation_and_key_actions_at_the_bottom_of_a_screen),
					action = { navController.navigate(R.id.action_materialKitFragment_to_bottomAppBarFragment) },
				), MaterialKit(
					id = 6,
					header = getString(R.string.badge),
					subHeader = getString(R.string.badges_are_used_to_convey_dynamic_information_such_as_a_count_or_status_a_badge_can_include_text_labels_or_numbers),
					action = { navController.navigate(R.id.action_materialKitFragment_to_badgeFragment) },
				), MaterialKit(
					id = 7,
					header = getString(R.string.checkbox),
					subHeader = getString(R.string.checkboxes_allow_users_to_select_one_or_more_items_from_a_set_and_can_be_used_to_turn_an_option_on_or_off_they_re_a_kind_of_selection_control_that_helps_users_make_a_choice_from_a_set_of_options),
					action = { navController.navigate(R.id.action_materialKitFragment_to_checkboxFragment) },
				), MaterialKit(
					id = 8,
					header = getString(R.string.chip),
					subHeader = getString(R.string.chips_help_people_enter_information_make_selections_filter_content_or_trigger_actions),
					action = { navController.navigate(R.id.action_materialKitFragment_to_chipFragment) },
				), MaterialKit(
					id = 9,
					header = getString(R.string.carousel),
					subHeader = getString(R.string.carousels_contains_a_collection_of_items_that_can_be_scrolled_on_and_off_the_screen),
					action = { navController.navigate(R.id.action_materialKitFragment_to_carouselFragment) },
				)
			)
		)
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		viewBinding = FragmentMaterialKitBinding.inflate(inflater, container, false)
		navController = findNavController()
		return viewBinding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewBinding.layoutHeader.toolbar.apply {
			title = getString(R.string.material_kit)
			setNavigationOnClickListener {
				navController.popBackStack()
			}
			menu.clear()
		}
		viewBinding.rcView.adapter = adapter
	}
}