package com.example.nikyshop.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nikyshop.R
import com.example.nikyshop.databinding.FragmentAddProductBinding
import com.example.nikyshop.model.entity.Product
import com.example.nikyshop.model.utils.Utils
import com.example.nikyshop.viewmodel.product.ProductViewModel
import com.example.nikyshop.viewmodel.product.ProductViewModelProvider

class AddProductFragment : Fragment(), ProductAdapter.MainItemsCallBack {


    lateinit var binding: FragmentAddProductBinding
    var inputEditTexts = false
    lateinit var productViewModel: ProductViewModel
    lateinit var Mainadapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //initialize binding view for read views xml
        binding = FragmentAddProductBinding.inflate(inflater, container, false)

        //initialize ViewModel
        productViewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(requireContext())
        ).get(ProductViewModel::class.java)

        //initialize Product Adapter For Recycler View
        Mainadapter = ProductAdapter(this, false)

        //return binding view for read views xml
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize a list of category for filter category
        val items = listOf(
            Utils.CATEGORY_GIRL,
            Utils.CATEGORY_BOY,
            Utils.CATEGORY_WOMEN,
            Utils.CATEGORY_MEN,
            Utils.CATEGORY_CHILD
        )
        //initialize adapter list of category for filter category
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_category, items)
        //set list to filter list category
        (binding.autoCompleteCategoryAddProduct as? AutoCompleteTextView)?.setAdapter(adapter)

        //on click button add
        binding.buttonAddAddProduct.setOnClickListener {
            //check is not empty fields and return true or false
            checkInputEditTexts()
            //check empty fields:false=empty   true=not empty
            if (inputEditTexts) {
                //create new product
                val product = Product(
                    0,
                    binding.editTextNameAddProduct.text.toString(),
                    binding.autoCompleteCategoryAddProduct.text.toString(),
                    binding.editTextPriceAddProduct.text.toString(),
                    binding.editTextUrlAddProduct.text.toString()
                )
                //add product into adapter list
                Mainadapter.addProduct(product)

                //add product into database
                productViewModel.addProduct(product)

                //go back page in admin fragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerMain, AdminFragment())
                    .commit()
            }
        }

    }

    //check is not empty fields and return true or false
    fun checkInputEditTexts() {
        if (binding.editTextNameAddProduct.text.isEmpty()) {
            binding.editTextNameAddProduct.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.editTextPriceAddProduct.text.isEmpty()) {
            binding.editTextPriceAddProduct.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.editTextUrlAddProduct.text.isEmpty()) {
            binding.editTextUrlAddProduct.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.autoCompleteCategoryAddProduct.text.toString().isEmpty()) {
            binding.menuCategoryAddProduct.error = "Empty!"
            inputEditTexts = false
        }
        if (binding.editTextPriceAddProduct.text.isNotEmpty()
            && binding.editTextNameAddProduct.text.isNotEmpty()
            && binding.editTextUrlAddProduct.text.isNotEmpty()
            && binding.autoCompleteCategoryAddProduct.text.toString().isNotEmpty()
        ) {
            inputEditTexts = true
        }
    }

    override fun onLongClick(product: Product) {

    }
}