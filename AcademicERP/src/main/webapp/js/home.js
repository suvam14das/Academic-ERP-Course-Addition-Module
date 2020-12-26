"use strict"
let initconf;
$(document).ready(function() {

    async function getInitData() {
        let response = await fetch('api/courses/allcourses', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        });

        initconf = await response.json();
        return initconf;
    }
    getInitData().then(function(initData){debugger;
        for(let index in initData["course_list"]) {
            let course = initData["course_list"][index];
            console.log(course);

            let element = '<tr id=' + (index) + ' class="btn-span" onclick=showcourse(this.id) >';
            element += '<td>'+course["courseCode"]+'</td>';
            element += '<td>'+course["name"]+'</td>';
            element += '<td>'+course["year"]+'</td>';
            element += '<td>'+course["term"]+'</td>';
            element += '<td>'+course["credits"]+'</td>';
            element += '<td>'+course["capacity"]+'</td>';
            element += '</tr>';


            //add all the table row in the table
            $('#courses-table > tbody:last-child').append(element);
        }

    });
});

function showcourse(index) {
    let courseInfo = createcoursedetails(index);
    $("#modal-title").text("Course Details");
    $("#modal-content").empty();
    $("#modal-content").append(courseInfo);
    $('#modal-display').modal('toggle');
    $('#modal-display').modal('show');
    console.log(newCourseInfo);
}

function createcoursedetails(index){

    let course = initconf["course_list"][index];
    console.log(course["name"]);
    let courseDetails = '<b>BASIC DETAILS : </b> <table class="table">';
    courseDetails += '<tr><td>COURSE NAME</td><td>'+course["name"] +'</td></tr>';
    courseDetails += '<tr><td>COURSE DESCRIPTION</td><td>'+course["description"]  +'</td></tr>';
    courseDetails += '<tr><td>COURSE CREDIT</td><td>'+course["credits"] +'</td></tr>';
    courseDetails += '<tr><td>COURSE CAPACITY</td><td>'+course["capacity"] +'</td></tr>';
    courseDetails += '<tr><td>COURSE YEAR</td><td>'+course["year"] +'</td></tr>';
    courseDetails += '<tr><td>COURSE TERM</td><td>'+course["term"] +'</td></tr>';
    courseDetails += '<tr><td>COURSE FACULTY</td><td>'+course["faculty"]["firstName"] + course["faculty"]["lastName"] +'</td></tr>';
    courseDetails += '</table><b>DOMAINS : </b>';

    let domains = course["domains"];
    let innercontent = '';
    for (let idx=0; idx<domains.length;idx++){

        let domainDetail = domains[idx];
        innercontent += domainDetail["program"] + " | ";

    }
    courseDetails += innercontent;

    courseDetails += '<br><br><b>SPECIALIZATION : </b>';
    let specializations = course["specializationList"] ;
    innercontent = '';
    for (let idx=0; idx<specializations.length;idx++){

        let specializationDetail = specializations[idx];
        innercontent += specializationDetail["name"] + " | ";

    }
    courseDetails += innercontent;

    courseDetails += '<br><br><b>PREREQUISITES NEEDED : </b><table class="table">'
    courseDetails += '<thead><tr><th>PREREQUISITE</th><th>YEAR</th><th>TERM</th></tr></thead>'
    let prereqs = course["preRequisite"];

    for (let idx=0; idx<prereqs.length;idx++){
        innercontent = '';
        let prereqDetails = prereqs[idx];
        innercontent += '<td>'+prereqDetails["name"] + '</td>';
        innercontent += '<td>'+prereqDetails["year"] + '</td>'
        innercontent += '<td>'+prereqDetails["term"] + '</td>'
        courseDetails += '<tr>'+innercontent+'</tr>';
    }
    courseDetails+='</table>';

    courseDetails += '<br><b>SCHEDULES : </b><table class="table">'
    courseDetails += '<thead><tr><th>ROOM</th><th>BUILDING</th><th>TIME</th><th>DAY</th></tr></thead>'
    let schedules = course["scheduleList"];

    for (let idx=0; idx<schedules.length;idx++){
        innercontent = '';
        let scheduleDetails = schedules[idx];
        innercontent += '<td>'+scheduleDetails["room"] + '</td>';
        innercontent += '<td>'+scheduleDetails["building"] + '</td>'
        innercontent += '<td>'+scheduleDetails["time"] + '</td>'
        innercontent += '<td>'+scheduleDetails["day"] + '</td>'
        courseDetails += '<tr>'+innercontent+'</tr>';
    }
    courseDetails+='</table>';

    return courseDetails;
}
