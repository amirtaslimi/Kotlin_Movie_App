package com.example.Project.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.Project.Feature.Register.Presentation.ViewModel.RegisterViewModel
import com.example.Project.databinding.ActivityMainRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityRegister : AppCompatActivity() {

    private lateinit var binding: ActivityMainRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialBinding()
        configViewModel()


    }


    private fun configViewModel() {
        viewModel.registerResult.observe(this) { result ->
            result.onSuccess { response ->
                // Handle successful registration
                Toast.makeText(this, response.description.en, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }.onFailure { throwable ->
                // Handle registration failure
                Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initialBinding() {
        binding = ActivityMainRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val studentNumber = binding.editTextStudentNumber.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            viewModel.registerUser(name, studentNumber, email, password)
        }
    }





}