const express = require('express')
const app = express()
var distance = require('google-distance');

app.get('/', (req, res) => res.send('Cao Cao'))
app.post('/location',(req,res)=>{
    var origin =req.body.origin;// req.body.origin;
    var destination =req.body.destination;// req.body.destination;
    
    distance.get({origin, destination}, function (err, distances) {
        if (!err){
            console.log(distances);
            res.status(200).send(distances);
         
        }else{
            console.log('Invalid!');
            res.status(400).send("Invalid!");
           
        }
    })
});
app.get('/glocation/a1/:origin/a2/:destination',(req,res)=>{
  
    var origin =req.param('origin');// req.body.origin;
    var destination =req.param('destination');// req.body.destination;
    
    distance.get({origin, destination}, function (err, distances) {
        if (!err){
            console.log(distances);
            res.status(200).send(distances);
         
        }else{
            console.log('Invalid!');
            res.status(400).send("Invalid!");
           
        }
    })
});

app.use(express.static('public'));

app.listen(process.env.PORT||3000, () => console.log('Web service run in port 3000!'))