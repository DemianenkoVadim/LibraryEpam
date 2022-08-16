var survey_options_first_name = document.getElementById('survey_options_first_name');
var add_more_author_first_name_field = document.getElementById('add_more_author_first_name_field');
var remove_field_author_first_name = document.getElementById('remove_field_author_first_name');

var survey_options_last_name = document.getElementById('survey_options_last_name');
var add_more_author_last_name_field = document.getElementById('add_more_author_last_name_field');
var remove_field_author_last_name = document.getElementById('remove_field_author_last_name');

add_more_author_first_name_field.onclick = function () {
    var newAuthorFirstNameField = document.createElement('input');
    newAuthorFirstNameField.setAttribute('type', 'text');
    newAuthorFirstNameField.setAttribute('name', 'authorFirstName');
    newAuthorFirstNameField.setAttribute('class', 'form-control');
    newAuthorFirstNameField.setAttribute('id', 'exampleInputBookAuthorFirstName1');
    survey_options_first_name.appendChild(newAuthorFirstNameField);
}

add_more_author_last_name_field.onclick = function () {
    var newAuthorLastNameField = document.createElement('input');
    newAuthorLastNameField.setAttribute('type', 'text');
    newAuthorLastNameField.setAttribute('name', 'authorLastName');
    newAuthorLastNameField.setAttribute('class', 'form-control');
    newAuthorLastNameField.setAttribute('id', 'exampleInputBookAuthorLastName1');
    survey_options_last_name.appendChild(newAuthorLastNameField);
}

remove_field_author_first_name.onclick = function () {
    var input_tag_first_name = survey_options_first_name.getElementsByTagName('input');
    if (input_tag_first_name.length > 1) {
        survey_options_first_name.removeChild(input_tag_first_name[(input_tag_first_name.length - 1)]);
    }
}

remove_field_author_last_name.onclick = function () {
    var input_tag_last_name = survey_options_last_name.getElementsByTagName('input');
    if (input_tag_last_name.length > 1) {
        survey_options_last_name.removeChild(input_tag_last_name[(input_tag_last_name.length - 1)]);
    }
}
