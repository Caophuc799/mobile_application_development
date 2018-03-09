const express = require('express')
const app = express()
var distance = require('google-distance');
var bodyParser = require('body-parser');
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies

app.get('/', (req, res) => res.send('Cao Cao'))
app.post('/location',function(req, res){
   console.log(req.body)
    var origin = req.body.origin;
    var destination = req.body.destination;
    console.log(origin);
    console.log(destination);
    
    distance.get({origin, destination},function (err, distances) {
        if (!err){
            console.log(distances);
            res.status(200).send(
                {
                    "success": true,
                    "error_code": null,
                    "message": 'Successfully get distance data!',
                    "data": distances
                }   
        );
         
        }else{
            console.log('Invalid!');
            res.status(400).send(
                {
                    "success": false,
                    "error_code": 3,
                    "message": 'Invalid data!',
                    "data": null
                } 

            );
           
        }
    })
});
app.get('/glocation/a1/:origin/a2/:destination',(req,res)=>{
  
    var origin =req.param('origin').toString();// req.body.origin;
    var destination = req.param('destination').toString();// req.body.destination;
    // var origin =  '37.871601,-122.269104';
    // var destination = '37.772886,-122.423771';
    distance.get({origin, destination}, function (err, distances) {
        if (!err){
            console.log(distances);
            res.status(200).send(
                {
                    "success": true,
                    "error_code": null,
                    "message": 'Successfully get distance data!',
                    "data": distances
                }   
        );
         
        }else{
            console.log('Invalid!');
            res.status(400).send(
                {
                    "success": false,
                    "error_code": 3,
                    "message": 'Invalid data!',
                    "data": null
                } 

            );
           
        }
    })
});

app.use(express.static('public'));

app.listen(process.env.PORT||3000, () => console.log('Web service run in port 3000!'))