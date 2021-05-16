
const showScenario = document.getElementById('show-dropdown-scenario');

showScenario.addEventListener('click', ()=>{
    console.log('clidk');
    document.getElementById("scenarioForTournament").classList.toggle("show");  
})