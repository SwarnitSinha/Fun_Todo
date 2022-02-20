package com.fun_todo.memesandtodocombine.ui.meme

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.fun_todo.memesandtodocombine.databinding.FragmentMemeBinding


class MemeFragment : Fragment() {

    //Binding in fragment is a little bit different than in activities
    private var _binding: FragmentMemeBinding? = null
    private val binding get() = _binding!!

    private var currentImageUrl: String? = null
    // This property is only valid between onCreateView and
    // onDestroyView.


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        loadMeme()
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemeBinding.inflate(inflater,container,false)
        val view = binding.root

        loadMeme()

        val shareBtn: Button = binding.shareBtn
        shareBtn.setOnClickListener {shareMeme()}


        val nextBtn : Button = binding.nextBtn
        nextBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            loadMeme()
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun loadMeme(){

//        val queue = Volley.newRequestQueue(context)
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {response ->
                currentImageUrl = response.getString("url")
                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
//                        progressBar.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        return false
                    }
                }).into(binding.imageView1)
            },
            { error ->
                Toast.makeText(context, "Something went wrong $error", Toast.LENGTH_SHORT).show()
            })
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)

    }

//    override fun onStop() {
//        VolleySingleton.getInstance((context)).deleteQueue()
//        Glide.get(requireContext()).clearMemory()
//        super.onStop()
//    }




    private fun shareMeme(){
        Toast.makeText(context,"Share button is clicked",Toast.LENGTH_SHORT).show()
        val intent= Intent()
        intent.action=Intent.ACTION_SEND
        intent.type = "text/plain"

        intent.putExtra(Intent.EXTRA_TEXT,
            "Hey Check out this meme: $currentImageUrl")
        startActivity(Intent.createChooser(intent,"Share To:"))
    }
}