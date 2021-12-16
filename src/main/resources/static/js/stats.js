const totalRequests = document.getElementById('totalReqeusts')

const allComments = []

const displayComments = (comments) => {
    totalRequests.innerHTML = asComment(comments)

}

function asComment(s) {
    return `<div id='totalRequests'>${s.authRequests}</div>`

}

fetch(`http://localhost:8080/statistics`).
then(response => response.json()).
then(data => {
    displayComments(data)
})
