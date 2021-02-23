const makePayment = () => {
    event.preventDefault()
    console.log("here");
    var email = document.getElementById('email').value;
    var firstName = document.getElementById('firstName').value;
    var lastName = document.getElementById('lastName').value;
    var address = document.getElementById('address').value;
    var phone = document.getElementById('phone').value;
    var city = document.getElementById('city').value;
    var zip = document.getElementById('zip').value;
    var amount = document.getElementById('amount').value;
    var reqBody = {
        "currency": "USD",
        "amount": 10000,
        "locale": "en_US",
        "customer": {
            firstName,
            lastName,
            email,
            phone,
            "dateOfBirth": {
                "day": 22,
                "month": 11,
                "year": 1999
            }
        },
        "billingAddress": {
            "nickName": firstName + " " + lastName,
            "street": address,
            "street2": "Montessori",
            city,
            zip,
            "country": "US",
            "state": "CA"
        },
        "environment": "TEST",
        "merchantRefNum": "1559900597607",
        "canEditAmount": true,
        "merchantDescriptor": {
            "dynamicDescriptor": "XYZ",
            phone
        },
        "displayPaymentMethods": ["skrill", "card"],
        "paymentMethodDetails": {
            "paysafecard": {
                "consumerId": "1232323"
            },
            "paysafecash": {
                "consumerId": "123456"
            },
            "sightline": {
                "consumerId": "123456",
                "SSN": "123456789",
                "last4ssn": "6789",
                "accountId": "1009688222"
            },
            "vippreferred": {
                "consumerId": "550726575",
                "accountId": "1679688456"
            }
        }
    };

    axios({
        method: 'post',
        url: 'http://localhost:9020/user/register',
        data: reqBody.customer
    }).then(function (response) {
        reqBody.singleUseCustomerToken = response.data;
        paysafe.checkout.setup("cHVibGljLTc3NTE6Qi1xYTItMC01ZjAzMWNiZS0wLTMwMmQwMjE1MDA4OTBlZjI2MjI5NjU2M2FjY2QxY2I0YWFiNzkwMzIzZDJmZDU3MGQzMDIxNDUxMGJjZGFjZGFhNGYwM2Y1OTQ3N2VlZjEzZjJhZjVhZDEzZTMwNDQ=    ",
            reqBody, function (instance, error, result) {
                if (result && result.paymentHandleToken) {
                    axios({
                        method: 'post',
                        url: 'http://localhost:9020/makePayment',
                        data: {
                            email,
                            "paymentHandleToken": result.paymentHandleToken,
                            "amount": result.amount,
                            "customerOperation": result.customerOperation
                        }
                    }).then(response => {
                        if (response.data != null) {
                            instance.showSuccessScreen("Payment Successful!");
                        } else {
                            instance.showFailureScreen("Payment Declined");
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                } else {
                    console.error(error);
                    instance.showFailureScreen("Payment Declined");
                }
            }, function (stage, expired) {
                switch (stage) {
                    case "PAYMENT_HANDLE_NOT_CREATED": // Handle the scenario
                    case "PAYMENT_HANDLE_CREATED": // Handle the scenario
                    case "PAYMENT_HANDLE_REDIRECT": // Handle the scenario
                    case "PAYMENT_HANDLE_PAYABLE": // Handle the scenario
                    default: // Handle the scenario
                }
            });
    }).catch(function (error) {
        console.log(error);
    });
}


