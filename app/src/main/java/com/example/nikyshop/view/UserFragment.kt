package com.example.nikyshop.view

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikyshop.databinding.FragmentUserBinding
import com.example.nikyshop.model.entity.Product
import com.example.nikyshop.model.utils.Utils
import com.example.nikyshop.viewmodel.product.ProductViewModel
import com.example.nikyshop.viewmodel.product.ProductViewModelProvider


class UserFragment : Fragment(), ProductAdapter.MainItemsCallBack, View.OnClickListener,
    AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentUserBinding
    lateinit var mainAdapter: ProductAdapter
    lateinit var productViewModel: ProductViewModel
    private val TAG = "UserFragment"
    //category list filter
    val items = listOf(
        "none",
        Utils.CATEGORY_GIRL,
        Utils.CATEGORY_BOY,
        Utils.CATEGORY_WOMEN,
        Utils.CATEGORY_MEN,
        Utils.CATEGORY_CHILD
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initilize view binding
        binding = FragmentUserBinding.inflate(inflater, container, false)

        //initialize viewmodel
        productViewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(requireContext())
        ).get(ProductViewModel::class.java)

        //get errors query and log it
        productViewModel.getErrors().observe(requireActivity(), Observer {
            Log.i(TAG, it)
        })

        // return view binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize spinner ( for filter category view )
        var spinner: Spinner

        spinner = binding.spinnerMain as Spinner

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_item, items
        )

        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = this;



        //query for get all products
        productViewModel.getProducts().observe(requireActivity(), Observer { list ->

            //initialize and set list to adapter list and recycler view user
            mainAdapter = ProductAdapter(this, false)
            mainAdapter.setProducts(list as ArrayList<Product>)
            binding.recViewUserFragment.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            binding.recViewUserFragment.adapter = mainAdapter

        })
    }

    override fun onLongClick(product: Product) {

    }


    //method for reaction selected filter items
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when (position) {

            0 -> {
                productViewModel.getProducts().observe(requireActivity(),
                    Observer { list ->
                        mainAdapter.setProducts(list as java.util.ArrayList<Product>)
                    })
            }
            1 -> {
                productViewModel.getProductWithCategory(items.get(1)).observe(requireActivity(),
                    Observer { list ->
                        mainAdapter.setProducts(list as java.util.ArrayList<Product>)
                    })
            }
            2 -> {
                productViewModel.getProductWithCategory(items.get(2)).observe(requireActivity(),
                    Observer { list ->
                        mainAdapter.setProducts(list as java.util.ArrayList<Product>)
                    })
            }
            3 -> {
                productViewModel.getProductWithCategory(items.get(3)).observe(requireActivity(),
                    Observer { list ->
                        mainAdapter.setProducts(list as java.util.ArrayList<Product>)
                    })
            }
            4 -> {
                productViewModel.getProductWithCategory(items.get(4)).observe(requireActivity(),
                    Observer { list ->
                        mainAdapter.setProducts(list as java.util.ArrayList<Product>)
                    })
            }
            5 -> {
                productViewModel.getProductWithCategory(items.get(5)).observe(requireActivity(),
                    Observer { list ->
                        mainAdapter.setProducts(list as java.util.ArrayList<Product>)
                    })
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onClick(v: View?) {
    }


}