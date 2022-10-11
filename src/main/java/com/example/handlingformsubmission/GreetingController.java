package com.example.handlingformsubmission;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RestController
public class GreetingController {

	@GetMapping("/greeting")
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new Greeting());
		return "greeting";
	}

	@PostMapping("/greeting")
	public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
		model.addAttribute("greeting", greeting);
		return "result";
	}

	@GetMapping("/")
	public String greetingForm() throws Exception {
		System.out.println(" *********************** Got Request ===========================");
		OkHttpClient client = new OkHttpClient().newBuilder()
				.build();
		Request request = new Request.Builder()
				.url("http://192.168.1.2/control/event.jpg?sequence=head")
				.method("GET", null)
				.build();
		Response response = client.newCall(request).execute();
		if(response.isSuccessful()){
			ResponseBody resImage = response.body();
			try (FileOutputStream stream = new FileOutputStream("abc.jpg")) {
				stream.write(resImage.bytes());
			}
		}
		return "pong";
	}

	@PostMapping("/")
	public String greetingFor() throws Exception {
		System.out.println(" *********************** Got Request POST===========================");
		OkHttpClient client = new OkHttpClient().newBuilder()
				.build();
		Request request = new Request.Builder()
				.url("http://192.168.1.2/control/event.jpg?sequence=head")
				.method("GET", null)
				.build();
		Response response = client.newCall(request).execute();
		if(response.isSuccessful()){
			ResponseBody resImage = response.body();
			try (FileOutputStream stream = new FileOutputStream("abc.jpg")) {
				stream.write(resImage.bytes());
			}
		}
		return "pong";
	}

}
