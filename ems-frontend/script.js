const API_URL = "http://localhost:8080/employees"

// ================= PAGE LOAD =================

document.addEventListener("DOMContentLoaded", function(){

    if(document.getElementById("employeeTable")){
        loadEmployees()
    }

    if(document.getElementById("department")){
        loadDepartmentsDropdown()
    }

    if(document.getElementById("deptStats")){
        loadDepartmentStats()
    }

    if(document.getElementById("barChart") || document.getElementById("pieChart")){
        loadCharts()
    }

})

// ================= EMPLOYEES =================

function loadEmployees(){

fetch(API_URL)
.then(res => res.json())
.then(data => {

let total = document.getElementById("totalEmployees")
if(total) total.innerText = data.length

let table = document.getElementById("employeeTable")
if(!table) return

table.innerHTML = ""

data.forEach(emp => {

table.innerHTML += `
<tr>
<td>${emp.id}</td>
<td>${emp.name}</td>
<td>${emp.email}</td>
<td>${emp.department || "-"}</td>
<td>

<button class="btn btn-warning btn-sm"
onclick="editEmployee(${emp.id},'${emp.name}','${emp.email}','${emp.department}')">
Edit
</button>

<button class="btn btn-danger btn-sm"
onclick="deleteEmployee(${emp.id})">
Delete
</button>

</td>
</tr>
`

})

loadDepartmentFilter()

})

}

// ================= ADD =================

function addEmployee(){

let name = document.getElementById("name").value
let email = document.getElementById("email").value
let department = document.getElementById("department").value

if(!name || !email || !department){
alert("Fill all fields")
return
}

fetch(API_URL,{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({ name,email,department })
})
.then(()=> loadEmployees())

}

// ================= DELETE =================

function deleteEmployee(id){

fetch(API_URL + "/" + id,{ method:"DELETE" })
.then(()=> loadEmployees())

}

// ================= SEARCH =================

function searchEmployee(){

let input = document.getElementById("searchInput").value.toLowerCase()
let rows = document.querySelectorAll("#employeeTable tr")

rows.forEach(row => {

let name = row.children[1].innerText.toLowerCase()
row.style.display = name.includes(input) ? "" : "none"

})

}

// ================= EDIT =================

function editEmployee(id,name,email,department){

let modal = document.getElementById("editModal")
if(!modal) return

modal.style.display="flex"

document.getElementById("editId").value=id
document.getElementById("editName").value=name
document.getElementById("editEmail").value=email
document.getElementById("editDepartment").value=department

}

function closeModal(){
let modal = document.getElementById("editModal")
if(modal) modal.style.display="none"
}

function updateEmployee(){

let id = document.getElementById("editId").value
let name = document.getElementById("editName").value
let email = document.getElementById("editEmail").value
let department = document.getElementById("editDepartment").value

fetch(API_URL + "/" + id, {
method: "PUT",
headers:{ "Content-Type":"application/json" },
body: JSON.stringify({ name,email,department })
})
.then(()=> {
closeModal()
loadEmployees()
})

}

// ================= DEPARTMENTS =================

let departments = ["IT", "HR", "Finance", "Marketing"]

function loadDepartmentsDropdown(){

let dropdown = document.getElementById("department")
if(!dropdown) return

dropdown.innerHTML = `<option value="">Select Department</option>`

departments.forEach(dept => {
dropdown.innerHTML += `<option value="${dept}">${dept}</option>`
})

}

// ================= FILTER =================

function loadDepartmentFilter(){

fetch(API_URL)
.then(res => res.json())
.then(data => {

let unique = [...new Set(data.map(emp => emp.department).filter(d => d))]

let dropdown = document.getElementById("deptFilter")
if(!dropdown) return

dropdown.innerHTML = `<option value="">Filter by Department</option>`

unique.forEach(dept => {
dropdown.innerHTML += `<option value="${dept}">${dept}</option>`
})

})

}

function filterByDepartment(){

let selected = document.getElementById("deptFilter").value.toLowerCase()
let rows = document.querySelectorAll("#employeeTable tr")

rows.forEach(row => {

let dept = row.children[3].innerText.toLowerCase()
row.style.display = (selected === "" || dept === selected) ? "" : "none"

})

}

// ================= REPORT CHARTS =================

function loadCharts(){

fetch(API_URL)
.then(res => res.json())
.then(data => {

let deptCount = {}

data.forEach(emp => {
if(emp.department){
deptCount[emp.department] = (deptCount[emp.department] || 0) + 1
}
})

let labels = Object.keys(deptCount)
let values = Object.values(deptCount)

// BAR
let barCtx = document.getElementById("barChart")
if(barCtx){
new Chart(barCtx, {
type: "bar",
data: {
labels,
datasets: [{ label:"Employees", data: values }]
},
options:{ responsive:true, maintainAspectRatio:false }
})
}

// PIE
let pieCtx = document.getElementById("pieChart")
if(pieCtx){
new Chart(pieCtx, {
type: "pie",
data: {
labels,
datasets: [{ data: values }]
}
})
}

})

}

// ================= DEPARTMENT STATS =================

function loadDepartmentStats(){

let list = document.getElementById("deptStats")
if(!list) return

fetch(API_URL)
.then(res => res.json())
.then(data => {

let deptCount = {}

data.forEach(emp => {
if(emp.department){
deptCount[emp.department] = (deptCount[emp.department] || 0) + 1
}
})

list.innerHTML = ""

for(let dept in deptCount){
list.innerHTML += `<li>${dept} → ${deptCount[dept]} Employees</li>`
}

})

}

// ================= LEAVE MANAGEMENT =================

let leaves = []

function applyLeave(){

let name = document.getElementById("empName").value
let start = document.getElementById("startDate").value
let end = document.getElementById("endDate").value

if(!name || !start || !end){
alert("Fill all fields")
return
}

let days = (new Date(end) - new Date(start)) / (1000*60*60*24) + 1

leaves.push({ name,start,end,days })

renderLeaves()

}

function renderLeaves(){

let table = document.getElementById("leaveTable")
if(!table) return

table.innerHTML = ""

leaves.forEach(l => {
table.innerHTML += `
<tr>
<td>${l.name}</td>
<td>${l.start}</td>
<td>${l.end}</td>
<td>${l.days}</td>
</tr>
`
})

}

// ================= EXTRA =================

function toggleDarkMode(){
document.body.classList.toggle("dark-mode")
}

function exportPDF(){

fetch(API_URL)
.then(res => res.json())
.then(data => {

const { jsPDF } = window.jspdf
let doc = new jsPDF()

doc.text("Employee Report", 14, 15)

let rows = data.map(emp => [
emp.id, emp.name, emp.email, emp.department
])

doc.autoTable({
head:[["ID","Name","Email","Department"]],
body:rows,
startY:20
})

doc.save("employees_report.pdf")

})

}