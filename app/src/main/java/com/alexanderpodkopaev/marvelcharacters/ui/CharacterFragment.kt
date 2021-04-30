package com.alexanderpodkopaev.marvelcharacters.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alexanderpodkopaev.marvelcharacters.R
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialContainerTransform
import dagger.android.support.DaggerFragment

class CharacterFragment : DaggerFragment(R.layout.character_fragment) {

    private val args: CharacterFragmentArgs by navArgs()

    lateinit var tvName: TextView
    lateinit var tvBio: TextView
    lateinit var ivImage: ImageView
    lateinit var clCharacter: ConstraintLayout
    lateinit var btnBack: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.TRANSPARENT
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                setAllContainerColors(
                    requireContext().resources.getColor(
                        R.color.colorBackground,
                        requireContext().theme
                    )
                )
            } else {
                setAllContainerColors(requireContext().resources.getColor(R.color.colorBackground))
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvName = view.findViewById(R.id.tv_name_detail)
        tvBio = view.findViewById(R.id.tv_bio_detail)
        ivImage = view.findViewById(R.id.iv_image_detail)
        clCharacter = view.findViewById(R.id.cl_character)
        btnBack = view.findViewById(R.id.iv_back)

        btnBack.setOnClickListener { findNavController().popBackStack() }

        with(args.character) {
            tvName.text = name
            tvBio.text = bio
            clCharacter.transitionName = name
            Glide.with(requireContext()).load(image)
                .placeholder(R.drawable.a)
                .into(ivImage)
        }
    }
}