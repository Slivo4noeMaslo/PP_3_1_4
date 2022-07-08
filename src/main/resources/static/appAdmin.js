$(async function() {
    await thisUser();
    await allUsers();
    await newUser();
});

async function thisUser() {
    fetch("http://localhost:8080/api/user")
        .then(res => res.json())
        .then(data => {
            // Добавляем информацию в шапку
            $('#headerUsername').append(data.email);
            let roles = data.roles.map(role => " " + role.name.substring(5));
            $('#headerRoles').append(roles);

            //Добавляем информацию в таблицу
            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.firstName}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.email}</td>
                <td>${roles}</td>)`;
            $('#userPanelBody').append(user);
        })
}

async function allUsers() {
    let table = $('#tbodyAllUserTable');
    table.empty()
    fetch("http://localhost:8080/api/admin")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let tableWithUsers = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.age}</td>
                            <td>${user.email}</td>
                            <td>${user.roles.map(role => " " + role.name.substring(5))}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal"
                                data-action="edit" data-userid="${user.id}" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal"
                                data-action="delete" data-userid="${user.id}" data-target="#delete">Edit</button>
                            </td>
                        </tr>)`;
                table.append(tableWithUsers);
            })
        })

    $('#tbodyAllUserTable').find('button').on('click', event => {
        let modal = $('#modal');
        let button = $(event.target);
        let buttonUserId = button.attr('data-userid');
        let buttonAction = button.attr('data-action');

        modal.attr('data-userid', buttonUserId);
        modal.attr('data-action', buttonAction);
        modal.modal('show');
    })
}

// async function allRoles() {
//     let allRoles = [];
//     await fetch("http://localhost:8080/admin/roles")
//             .then(res => res.json())
//             .then(roles => {
//                 roles.forEach(role => {
//                     allRoles.push(role)
//                 })
//             });
//     return allRoles;
// }

async function newUser() {
    let allRoles = [];
    await fetch("http://localhost:8080/api/roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                allRoles.push(role)
            })
        })
    let formRoles = ``;
    allRoles.forEach(role => {
        let role1 = `<option value=\"${role.id}\">${role.name.substring(5)}</option>\n`;
        formRoles += role1;
    })
    $('#newUserRoles').append(formRoles);


    const form = document.forms["formNewUser"];

    form.addEventListener('submit', addNewUser)

    function addNewUser(e) {
        e.preventDefault();
        let newUserRoles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) newUserRoles.push({
                id : form.roles.options[i].value,
                name : form.roles.options[i].name
            })
        }
        fetch("http://localhost:8080/api/admin", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: form.firstName.value,
                lastName: form.lastName.value,
                age: form.age.value,
                email: form.email.value,
                password: form.password.value,
                roles: newUserRoles
            })
        }).then(() => {
            $('#allUsers').click();
            location.reload();
        })
    }

}

