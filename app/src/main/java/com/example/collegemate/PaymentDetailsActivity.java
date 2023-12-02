package com.example.collegemate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

import java.text.DecimalFormat;

public class PaymentDetailsActivity extends AppCompatActivity {

    CardForm cardForm;
    Button btnPayment;
    AlertDialog.Builder alertBuilder;
    Button btnCancelPayment;
    int roomSelectedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        cardForm = findViewById(R.id.cardForm);
        btnPayment = findViewById(R.id.btnProcessPayment);
        btnCancelPayment = findViewById(R.id.btnCancelPayment);
        Bundle bundle = getIntent().getExtras();
        roomSelectedPrice = getIntent().getExtras().getInt("ROOMPRICE", 0);

        cardForm.cardRequired(true);
        cardForm.expirationRequired(true);
        cardForm.cvvRequired(true);
        cardForm.postalCodeRequired(true);
        cardForm.cardholderName(CardForm.FIELD_REQUIRED);
        cardForm.mobileNumberRequired(true);
        cardForm.mobileNumberExplanation("Confirmation SMS will be sent once payment is successful");
        cardForm.actionLabel("Make Payment");
        cardForm.setup(PaymentDetailsActivity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);


        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardForm.isValid()) {
                    long userid = bundle.getLong("userId", -1);
                    alertBuilder = new AlertDialog.Builder(PaymentDetailsActivity.this);
                    alertBuilder.setTitle("Confirm details to proceed");
                    DecimalFormat df = new DecimalFormat("$#####.##");
                    alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                            "Postal Code: " + cardForm.getPostalCode() + "\n" +
                            "Transaction detail: room reservation\n" +
                            "Amount due: " + df.format(roomSelectedPrice) + "\n");
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(PaymentDetailsActivity.this, "Transaction Successful", Toast.LENGTH_SHORT).show();

                            Bundle paymentSuccessBundle = new Bundle();
                            int payAmount = roomSelectedPrice;
                            paymentSuccessBundle.putInt("AmountPaid",payAmount);
                            paymentSuccessBundle.putLong("userId", userid);
                            Intent backToSearchIntent = new Intent(PaymentDetailsActivity.this, FindAccommodationActivity.class);
                            backToSearchIntent.putExtras(paymentSuccessBundle);
                            //backToSearchIntent.putExtra("userId", userid);
                            startActivity(backToSearchIntent);
                            //onBackPressed();

                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(PaymentDetailsActivity.this, "Please enter the field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle cancelPayment = new Bundle();
                long userId = getIntent().getLongExtra("userId", -1);
                cancelPayment.putLong("userId", userId);
                Intent backToSearchIntent2 = new Intent(PaymentDetailsActivity.this, FindAccommodationActivity.class);
                backToSearchIntent2.putExtras(cancelPayment);
                startActivity(backToSearchIntent2);
                //onBackPressed();
            }
        });
    }
}