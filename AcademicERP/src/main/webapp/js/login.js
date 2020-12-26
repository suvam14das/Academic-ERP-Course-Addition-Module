"use strict"

//let adminloginform = document.getElementById('login');

document.getElementById('login').addEventListener('submit', async (e) => {


    let adminloginform = document.getElementById('login');
    console.log(adminloginform);
        e.preventDefault();
        e.stopPropagation();

    if (adminloginform.checkValidity() === true) {
        let x = document.getElementById("print");

        let response = await fetch('../api/employee/getlogin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                email: document.getElementById("loginemail").value,
                password: document.getElementById('loginpassword').value,
            })
        });

        let result = await response.json();
        debugger;
        console.log(result['id']);
        if( result['id'] == -1){
            x.innerText = "Not an admin";
        }else if( result['id'] >= 0){
            sessionStorage.setItem("loggedin_empid", result['id']);
            sessionStorage.setItem("loggedin_emp_name", result['emp_name'])
            window.location = "http://localhost:8080/newProject_war/";
        }else{
            x.innerText = "Incorrect email or password";
        }

    }
    adminloginform.classList.add('was-validated');
});
