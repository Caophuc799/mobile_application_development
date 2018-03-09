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
  
    var origin =req.param('origin');// req.body.origin;
    var destination =req.param('destination');// req.body.destination;
    
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