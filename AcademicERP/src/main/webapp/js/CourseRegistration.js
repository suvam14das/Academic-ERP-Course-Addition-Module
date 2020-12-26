"use strict"
$(document).ready(function() {
    let newCourseInfo = {}
    let errorList = [];

    async function getInitData() {
        let response = await fetch('api/courses/initconf', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        });

        let initconf = await response.json();
        initconf['weekDays'] = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
        return initconf;
    }

    async function getPreRequirementCourses(year, term) {

        let response = await fetch('api/courses/-1/year/'+year+'/term/'+term, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        });

        let prereqlist = await response.json();
        return prereqlist;
    }

    function displayErrorModal(errorListDisplay){
        $("#modal-display").hide();
        $("#modal-title").empty();
        $("#modal-content").empty();
        $("#modal-botton").empty();
        $("#modal-title").text("Error");
        let errorMessage = '<ul>';
        for(let idx in errorListDisplay){
            errorMessage += '<li>'+errorListDisplay[idx]+'</li>';
        }
        errorMessage += '</ul';
        $("#modal-content").html(errorMessage);
        $("#modal-botton").empty();
        $('#modal-display').modal('toggle');
        $('#modal-display').modal('show');
        $('#modal-show').css("visibility","visible");
    }
    function validateCourseCredit() {
        let credit = parseInt($("#course-credit").val());
        if($("#course-credit").val() == ""){
            errorList.push("Course Credit cannot be blank");
        }else if (Number.isNaN(credit) || credit < 0) {
            errorList.push("Invalid Course Credit Provided");
        }
    }

    function validateCourseCapacity() {
        let capacity = parseInt($("#course-capacity").val());
        if($("#course-capacity").val() == ""){
            errorList.push("Course Capacity cannot be blank");
        }else if (Number.isNaN(capacity) || capacity <= 0) {
            errorList.push("Invalid Course Capacity Provided");
        }
    }

    function validateCourseYear() {
        let year = parseInt($("#course-year").val());
        if($("#course-year").val() == ""){
            errorList.push("Course Year cannot be blank");
        }else if (Number.isNaN(year) || year < 0 || $("#course-year").val().length != 4) {
            errorList.push("Invalid Year Provided");
        }
    }

    function validateCourseTerm() {
        let term = parseInt($("#course-term").val());
        if($("#course-term").val() == ""){
            errorList.push("Course Term cannot be blank");
        }else if (Number.isNaN(term) || term <=0) {
            errorList.push("Invalid Term Provided");
        }
    }

    function validateCourseName() {
        if($("#course-name").val() == ""){
            errorList.push("Course Name cannot be blank");
        }
    }

    function validateCourseDescription() {
    }

    function validateCourseDomain() {
        if($("#domain-table > tbody > tr").length == 0){
            errorList.push("No Domain provided for the Course");
        }
    }

    function validateCourseSpecialization() {
        if($("#specialization-table > tbody > tr").length == 0){
            errorList.push("No Specialization provided for the Course");
        }
    }

    function validateSchedule(){
        if($("#schedule-table > tbody > tr").length == 0){
            errorList.push("No schedule provided for the Course");
        }
    }

    function validateFaculty(){
        if($("#course-faculty").find(":selected").val() == ""){
            errorList.push("No Faculty is selected for the Course");
        }
    }

    function validateForm() {
        validateCourseName();
        validateCourseDescription();
        validateCourseCredit();
        validateCourseCapacity();
        validateCourseYear();
        validateCourseTerm();
        validateFaculty();
        validateCourseDomain();
        validateCourseSpecialization();
        validateSchedule();
    }

    function getUserConfDivElement(left_content, right_content){
        return '<div className="row">'+'<div className="col-sm-4">'+left_content+'</div>'+
            '<div className="col-sm-8">'+right_content+'</div>'+'</div>';
    }

    function createUserConformation(){
        newCourseInfo = {}
        let courseDetails = '<b>BASIC DETAILS : </b> <table class="table">';
        courseDetails += '<tr><td>COURSE NAME</td><td>'+$("#course-name").val() +'</td></tr>';
        courseDetails += '<tr><td>COURSE DESCRIPTION</td><td>'+$("#course-description").val()  +'</td></tr>';
        courseDetails += '<tr><td>COURSE CREDIT</td><td>'+$("#course-credit").val()  +'</td></tr>';
        courseDetails += '<tr><td>COURSE CAPACITY</td><td>'+$("#course-capacity").val()  +'</td></tr>';
        courseDetails += '<tr><td>COURSE YEAR</td><td>'+$("#course-year").val()  +'</td></tr>';
        courseDetails += '<tr><td>COURSE TERM</td><td>'+$("#course-term").val()  +'</td></tr>';
        courseDetails += '<tr><td>COURSE FACULTY</td><td>'+$("#course-faculty").find(":selected").text()  +'</td></tr>';
        courseDetails += '</table><b>DOMAINS : </b><table class="table">';
        courseDetails += '<thead><tr><th>Domain</th></tr></thead>';

        let domains = $("#domain-table > tbody").children();
        let domainList = [];
        let innercontent = '';
        for (let idx=0; idx<domains.length;idx++){

            let domainDetail = domains[idx].children;
            innercontent += '<tr><td>'+domainDetail[0].textContent + "</td></tr>";
            domainList.push(domains[idx].id.match(/\d+$/)[0]);
        }
        courseDetails += innercontent+'</table>';
        courseDetails += '<b>SPECIALIZATION : </b><table class="table">';
        courseDetails += '<thead><tr><th>Specialization</th></tr></thead>';
        let specializations = $("#specialization-table > tbody").children();
        let specializationList = [];
        innercontent = '';
        for (let idx=0; idx<specializations.length;idx++){

            let specializationDetail = specializations[idx].children;
            innercontent += '<tr><td>'+specializationDetail[0].textContent + '</td></tr>';
            specializationList.push(specializations[idx].id.match(/\d+$/)[0]);
        }
        courseDetails += innercontent+'</table>';
        courseDetails += '<b>PREREQUISITES NEEDED : </b><table class="table">'
        courseDetails += '<thead><tr><th>PREREQUISITE</th><th>YEAR</th><th>TERM</th></tr></thead>'
        let prereqs = $("#pre-req-table > tbody").children();
        let prereqList = [];
        for (let idx=0; idx<prereqs.length;idx++){
            innercontent = '';
            let prereqDetails = prereqs[idx].children;
            innercontent += '<td>'+prereqDetails[0].textContent + '</td>';
            innercontent += '<td>'+prereqDetails[1].textContent + '</td>'
            innercontent += '<td>'+prereqDetails[2].textContent + '</td>'
            prereqList.push(prereqs[idx].id.match(/\d+$/)[0]);
            courseDetails += '<tr>'+innercontent+'</tr>';
        }
        courseDetails+='</table>';
        courseDetails += '<b>SCHEDULES : </b><table class="table">'
        courseDetails += '<thead><tr><th>ROOM</th><th>BUILDING</th><th>TIME</th><th>DAY</th></tr></thead>'
        let schedules = $("#schedule-table > tbody").children();
        let scheduleList = [];
        for (let idx=0; idx<schedules.length;idx++){
            innercontent = '';
            let scheduleDetails = schedules[idx].children;
            innercontent += '<td>'+scheduleDetails[0].textContent + '</td>';
            innercontent += '<td>'+scheduleDetails[1].textContent + '</td>'
            innercontent += '<td>'+scheduleDetails[2].textContent + '</td>'
            innercontent += '<td>'+scheduleDetails[3].textContent + '</td>'
            courseDetails += '<tr>'+innercontent+'</tr>';
            let schedule_det = {};
            schedule_det['room'] = scheduleDetails[0].textContent;
            schedule_det['building'] = scheduleDetails[1].textContent;
            schedule_det['time'] = scheduleDetails[2].textContent;
            schedule_det['day'] = scheduleDetails[3].textContent;
            scheduleList.push(schedule_det);
        }
        courseDetails+='</table>';

        newCourseInfo['course_name'] = $("#course-name").val();
        newCourseInfo['course_description'] = $("#course-description").val();
        newCourseInfo['course_credit'] = $("#course-credit").val();
        newCourseInfo['course_capacity'] = $("#course-capacity").val();
        newCourseInfo['course_year'] = $("#course-year").val();
        newCourseInfo['course_term'] = $("#course-term").val();
        newCourseInfo['course_faculty'] = $("#course-faculty").find(":selected").val();
        newCourseInfo['domain_list'] = domainList;
        newCourseInfo['specialization_list'] = specializationList;
        newCourseInfo['prerequisites_list'] = prereqList;
        newCourseInfo['schedule_list'] = scheduleList;
        return courseDetails;
    }

    function resetForm(){
        $("#course-name").val("");
        $("#course-description").val("");
        $("#course-term").val("");
        $("#course-year").val("");
        $("#course-credit").val("");
        $("#course-capacity").val("");
        $("#pre-year").val("");
        $("#pre-term").val("");
        $("#schedule-room").val("");
        $("#schedule-building").val("");
        $("#schedule-time").val("")

        $("#course-specialization-body").hide();
        $("#course-domain-body").hide();
        $("#basic-details-body").hide();
        $("#course-pre-req-body").hide();
        $("#course-schedule-body").hide();

        $("#course-faculty").empty();
        $("#course-domain").empty();
        $("#course-specialization").empty();
        $("#schedule-day").empty();


        $("#domain-table > tbody").empty();
        $("#specialization-table > tbody").empty();
        $("#schedule-table > tbody").empty();
        $("#pre-req-table > tbody").empty();
        $("#pre-course").empty();
        $("#add-pre-req").attr("disabled", true);

        populateInitdata();

        $("#basic-details-error").empty();
        $("#domain-error").empty();
        $("#specialization-error").empty();
        $("#pre-requisite-error").empty();
        $("#schedule-details-error").empty();

        $("#basic-details-error").css("visibility", "hidden");
        $("#domain-error").css("visibility", "hidden");
        $("#specialization-error").css("visibility", "hidden");
        $("#pre-requisite-error").css("visibility", "hidden");
        $("#schedule-details-error").css("visibility", "hidden");
    }

    function showSystemConfirmation(courseCode){
        $("#modal-display").hide();
        $("#modal-title").empty();
        $("#modal-content").empty();
        $("#modal-botton").empty();
        $("#modal-title").text("Course Creation : System Confirmation");
        $("#modal-content").html("Course has been successfully registered.<br/>"+
            "Course code is <b>"+courseCode+"</b>");
        $("#modal-botton").empty();
        $('#modal-display').modal('toggle');
        $('#modal-display').modal('show');
    }

    function populateInitdata(){
        //initializing data in the page
        getInitData().then(function(initData){
            if(initData[''] === undefined || initData['error_list'].length == 0){
                for (let index in initData["domain_list"]) {
                    let option = '<option value=' + initData["domain_list"][index]["domainId"] + '>'
                        + initData["domain_list"][index]['program'] + '</option>';
                    $("#course-domain").append(option);
                }
                for (let index in initData["specialization_list"]) {
                    let option = '<option value=' + initData["specialization_list"][index]["id"] + '>'
                        + (initData["specialization_list"][index]['code'] + " : "
                            + initData["specialization_list"][index]['name']) + '</option>';
                    $("#course-specialization").append(option);
                }
                for (let index in initData["weekDays"]) {
                    let option = '<option>' + initData["weekDays"][index] + '</option>';
                    $("#schedule-day").append(option);
                }
                for (let index in initData["faculty_list"]) {
                    let option = '<option value='+initData["faculty_list"][index]['employeeId']+'>'
                        + (initData["faculty_list"][index]['firstName']+' '+
                            initData["faculty_list"][index]['lastName']) + '</option>';
                    $("#course-faculty").append(option);
                }
                $('#modal-show').css("visibility","hidden");
            }else{
                displayErrorModal(initData['error_list']);
            }


        });
    }


    $(document).on("click", "#pre-req-table button.upd-btn", function () {
        let updRow = $(this).closest('tr');
        let data = updRow.children();
        $("#pre-course").val(data[0].innerText);
        $("#pre-year").val(data[1].innerText);
        $("#pre-term").val(data[2].innerText);
        $(("#" + updRow.attr("id"))).remove();
    });

    $(document).on("click", "#pre-req-table button.del-btn", function () {
        let delRow = $(this).closest('tr');
        $(("#" + delRow.attr("id"))).remove();
    });

    $(document).on("click", "#domain-table button.del-btn", function () {
        let delRow = $(this).closest('tr');
        $(("#" + delRow.attr("id"))).remove();
    });

    $(document).on("click", "#specialization-table button.del-btn", function () {
        let delRow = $(this).closest('tr');
        $(("#" + delRow.attr("id"))).remove();
    });

    $("#add-pre-req").click(function () {
        let add_err = [];
        let added_pre = $("#pre-req-table > tbody").children();
        for(let idx=0;idx<added_pre.length;idx++){
            if(added_pre[idx].id === ("pre-req-row-" + $("#pre-course").find(":selected").val())){
                add_err.push("Prerequisite["+added_pre[idx].children[0].textContent+"] already added");
            }
        }
        if(add_err.length > 0){
            let errorMessage = '<b>Error Generated</b><ul>';
            for (let idx in add_err) {
                errorMessage = errorMessage + '<li>' + add_err[idx] + '</li>';
            }
            errorMessage = errorMessage + '</ul>';
            $("#pre-requisite-error").empty();
            $("#pre-requisite-error").append(errorMessage);
            $("#pre-requisite-error").css("visibility", "visible");
            return;
        }

        //create a table row which is to be added
        let element = '<tr id=' + ("pre-req-row-" + $("#pre-course").find(":selected").val()) + '>' +
            '<td name="course">' + $("#pre-course").find(":selected").text() + '</td>' +
            '<td name="year">' + $("#pre-year").val() + '</td>' +
            '<td name="term">' + $("#pre-term").val() + '</td>' +
            //'<td class="btn-col"><button type="button" class="btn btn-warning upd-btn">Update</button></td>'+
            '<td class="btn-col"><button type="button" class="btn btn-danger del-btn right">Delete</button></td>' +
            '</tr>';
        $('#pre-req-table > tbody:last-child').append(element);
        $("#pre-requisite-error").empty();
        $("#pre-requisite-error").css("visibility", "hidden");
    });


    $(document).on("click", "#schedule-table button.upd-btn", function () {
        let updRow = $(this).closest('tr');
        let data = updRow.children();
        $("#schedule-room").val(data[0].innerText);
        $("#schedule-building").val(data[1].innerText);
        $("#schedule-time").val(data[2].innerText);
        $("#schedule-day").val(data[3].innerText);
        $(("#" + updRow.attr("id"))).remove();
    });

    $(document).on("click", "#schedule-table button.del-btn", function () {
        let delRow = $(this).closest('tr');
        $(("#" + delRow.attr("id"))).remove();
    });

    $("#add-schedule").click(function () {
        let scheduleErrorList = [];
        if ($("#schedule-room").val() === undefined || $("#schedule-room").val() == "")
            scheduleErrorList.push("Room cannot be blank");
        if ($("#schedule-time").val() === undefined || $("#schedule-time").val() == "")
            scheduleErrorList.push("Time cannot be empty");
        if ($("#schedule-day").val() === undefined || $("#schedule-day").val() == "")
            scheduleErrorList.push("Day cannot be empty");

        if (scheduleErrorList.length > 0) {
            let errorMessage = '<b>Error Generated</b><ul>';
            for (let idx in scheduleErrorList) {
                errorMessage = errorMessage + '<li>' + scheduleErrorList[idx] + '</li>';
            }
            errorMessage = errorMessage + '</ul>';
            $("#schedule-details-error").empty();
            $("#schedule-details-error").append(errorMessage);
            $("#schedule-details-error").css("visibility", "visible");
            return;
        }
        $("#schedule-details-error").empty();
        $("#schedule-details-error").css("visibility", "hidden");

        let curr = $('#schedule-table > tbody ').children().length + 1;
        //create a table row which is to be added
        let element = '<tr id=' + ("schedule-row-" + curr) + '>' +
            '<td name="room">' + $("#schedule-room").val() + '</td>' +
            '<td name="building">' + $("#schedule-building").val() + '</td>' +
            '<td name="time">' + $("#schedule-time").val() + '</td>' +
            '<td name="date">' + $("#schedule-day").val() + '</td>' +
            '<td class="btn-col"><button type="button" class="btn btn-warning upd-btn right">Update</button></td>' +
            '<td class="btn-col"><button type="button" class="btn btn-danger del-btn right">Delete</button></td>' +
            '</tr>';

        //add all the table row in the table
        $('#schedule-table > tbody:last-child').append(element);

        //clear the input fields
        $("#schedule-room").val("");
        $("#schedule-building").val("");
        $("#schedule-time").val("");

    });

    $("#add-domain").click(function () {
        let add_err = [];
        let added_domain = $("#domain-table > tbody").children();
        for(let idx=0;idx<added_domain.length;idx++){
            if(added_domain[idx].id === ("domain-row-" + $("#course-domain").find(":selected").val())){
                add_err.push("Domain["+added_domain[idx].children[0].textContent+"] already added");
            }
        }
        if(add_err.length > 0){
            let errorMessage = '<b>Error Generated</b><ul>';
            for (let idx in add_err) {
                errorMessage = errorMessage + '<li>' + add_err[idx] + '</li>';
            }
            errorMessage = errorMessage + '</ul>';
            $("#domain-error").empty();
            $("#domain-error").append(errorMessage);
            $("#domain-error").css("visibility", "visible");
            return;
        }
        let element = '<tr id=' + ("domain-row-" + $("#course-domain").find(":selected").val()) + '>' +
            '<td name="domain">' + $("#course-domain").find(":selected").text() + '</td>' +
            '<td class="btn-col"><button type="button" class="btn btn-danger del-btn right">Delete</button></td>' +
            '</tr>';

        //add all the table row in the table
        $('#domain-table > tbody:last-child').append(element);
        $("#domain-error").empty();
        $("#domain-error").css("visibility", "visible");
    });

    $("#add-specialization").click(function () {
        let add_err = [];
        let added_spec = $("#specialization-table > tbody").children();
        for(let idx=0;idx<added_spec.length;idx++){
            if(added_spec[idx].id === ("specialization-row-" + $("#course-specialization").find(":selected").val())){
                add_err.push("Specilization["+added_spec[idx].children[0].textContent+"] already added");
            }
        }
        if(add_err.length > 0){
            let errorMessage = '<b>Error Generated</b><ul>';
            for (let idx in add_err) {
                errorMessage = errorMessage + '<li>' + add_err[idx] + '</li>';
            }
            errorMessage = errorMessage + '</ul>';
            $("#specialization-error").empty();
            $("#specialization-error").append(errorMessage);
            $("#specialization-error").css("visibility", "visible");
            return;
        }

        let element = '<tr id=' + ("specialization-row-" + $("#course-specialization").find(":selected").val()) + '>' +
            '<td name="specialization">' + $("#course-specialization").find(":selected").text() + '</td>' +
            '<td class="btn-col"><button type="button" class="btn btn-danger del-btn right">Delete</button></td>' +
            '</tr>';

        //add all the table row in the table
        $('#specialization-table > tbody:last-child').append(element);
        $("#specialization-error").empty();
        $("#specialization-error").attr("visibility", "hidden");
    });


    $("#get-pre-req").click(async function () {
        $("#pre-course").empty();
        $("#add-pre-req").attr("disabled", true);
        let year = parseInt($("#pre-year").val());
        let term = parseInt($("#pre-term").val());
        let preRequirementErrorList = [];
        if($("#pre-year").val() == ""){
            preRequirementErrorList.push("Course Year for Prerequisite cannot be blank");
        }else if (Number.isNaN(year) || year.toString().length != 4) {
            preRequirementErrorList.push("Invalid year provided");
        }
        if($("#pre-term").val() == ""){
            preRequirementErrorList.push("Course Term for Prerequisite cannot be blank");
        }else if (Number.isNaN(term) || term <= 0) {
            preRequirementErrorList.push("Invalid term provided");
        }
        if (preRequirementErrorList.length > 0) {
            let errorMessage = '<b>Error Generated</b><ul>';
            for (let idx in preRequirementErrorList) {
                errorMessage = errorMessage + '<li>' + preRequirementErrorList[idx] + '</li>';
            }
            errorMessage = errorMessage + '</ul>';
            $("#pre-requisite-error").empty();
            $("#pre-requisite-error").append(errorMessage);
            $("#pre-requisite-error").css("visibility", "visible");
            return;
        }
        $("#pre-requisite-error").empty();
        $("#pre-requisite-error").css("visibility", "hidden");
        getPreRequirementCourses(year, term).then(function(preReqList){
            if(preReqList["error_list"]!=undefined && preReqList["error_list"].length != 0){
                $("#modal-display").hide();
                $("#modal-title").empty();
                $("#modal-content").empty();
                $("#modal-botton").empty();
                $("#modal-title").text("");
                $("#modal-content").html(preReqList["error_list"][0]);
                $("#modal-botton").empty();
                $('#modal-display').modal('toggle');
                $('#modal-display').modal('show');

            }
            for (let index in preReqList["courses"]) {
                let option = '<option value='+preReqList["courses"][index]['courseId']+ '>'
                    + (preReqList["courses"][index]['courseCode']+" : "
                        +preReqList["courses"][index]['name']) + '</option>';
                $("#pre-course").append(option);
            }
            if(preReqList["courses"].length > 0 )
                $("#add-pre-req").attr("disabled", false);
        });

    });

    $(document).on("click", "#confirm-btn", async function () {
        let response = await fetch('api/courses/submit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(newCourseInfo)
        });

        let result = await response.json();
        if(result['error_list']=== undefined || result['error_list'].length == 0){
            resetForm();
            showSystemConfirmation(result["course_code"]);
            $('#modal-show').css("visibility","hidden");
        }else{
            displayErrorModal(result["error_list"]);
        }

    });

    $("#submit-course").click(function () {
        errorList = [];
        validateForm();
        if (errorList.length > 0) {

            displayErrorModal(errorList);
            /*
            let errorMessage = '';
            for (let idx in errorList) {
                errorMessage = errorMessage + '<li>' + errorList[idx] + '</li>';
            }
            errorMessage = errorMessage + '</ul>';
            $("#modal-title").text("Errors generated");
            $("#modal-content").empty();
            $("#modal-content").append(errorMessage);
            $('#modal-display').modal('toggle');
            $('#modal-display').modal('show');
            $('#modal-show').css("visibility","visible");

             */
        } else {
            let userConfirmationMessage = createUserConformation();
            $("#modal-title").empty();
            $("#modal-title").text("Course Creation : User Confirmation");
            $("#modal-content").empty();
            $("#modal-botton").empty();
            $("#modal-content").append(userConfirmationMessage);
            $("#modal-botton").append('<button id="confirm-btn" type="button" class="btn btn-info right">Confirm</button>')
            $('#modal-display').modal('toggle');
            $('#modal-display').modal('show');
            $('#modal-show').css("visibility","hidden");
        }

    });

    $("#course-specialization-header").click(function () {
        $("#course-specialization-body").toggle();
    });
    $("#course-domain-header").click(function () {
        $("#course-domain-body").toggle();
    });
    $("#basic-details-header").click(function () {
        $("#basic-details-body").toggle();
    });
    $("#course-pre-req-header").click(function () {
        $("#course-pre-req-body").toggle();
    });
    $("#course-schedule-header").click(function () {
        $("#course-schedule-body").toggle();
    });

    $("#course-pre-req-body").hide();
    $("#course-schedule-body").hide();
    $("#course-specialization-body").hide();
    $("#course-domain-body").hide();
    $("#basic-details-body").hide();


    $(".required").each(function(){
        let element = $(this);
        let content = $(this).text();
        content += '<span style="color: #ff0000">*</span>';
        $(this).html(content);
    });

    populateInitdata();
});