package com.example.nikyshop.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikyshop.R
import com.example.nikyshop.databinding.FragmentAdminBinding
import com.example.nikyshop.databinding.FragmentSignupBinding
import com.example.nikyshop.databinding.FragmentUserBinding
import com.example.nikyshop.model.entity.Product
import com.example.nikyshop.model.utils.Methods
import com.example.nikyshop.viewmodel.product.ProductViewModel
import com.example.nikyshop.viewmodel.product.ProductViewModelProvider
import java.util.ArrayList

class AdminFragment : Fragment(), ProductAdapter.MainItemsCallBack {

    lateinit var binding: FragmentAdminBinding
    lateinit var adapter: ProductAdapter
    lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize binding view for read views xml
        binding = FragmentAdminBinding.inflate(inflater, container, false)

        //initialize viewmodel
        productViewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(requireContext())
        ).get(ProductViewModel::class.java)

        //return binding view for read views xml
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //query to database for get products list
        productViewModel.getProducts().observe(requireActivity(), Observer { list ->

            //initialize and set list to adapter list and recyclerview admin fragment
            adapter = ProductAdapter(this, true)
            adapter.setProducts(list as ArrayList<Product>)

            binding.recViewAdminFragment.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            binding.recViewAdminFragment.adapter = adapter

        })


        //on click add product button
        binding.btnAddProduct.setOnClickListener {

            //go to add product form page
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerMain, AddProductFragment())
                .addToBackStack("admin")
                .commit()
        }

    }

    //for on long click on items list product for delete item
    override fun onLongClick(product: Product) {

        Methods.showDeleteProductDialog(requireContext()) {
            productViewModel.deleteProduct(product)
            adapter.deleteProduct(product)

        }
    }
}