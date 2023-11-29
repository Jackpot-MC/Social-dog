
async function rest_get(url){
	try{
		let res = await fetch(url);
		return await res.json();
	} catch(e){
		console.log(e);
	}
}

async function rest_create(url, data){
	try{
		let res = await fetch(url, {
			method: "POST",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(data),
			});
			return await res.json();
		}catch(e) {
			console.log(e);
	}
}

async function rest_update(url, data){
	try{
		let res = await fetch(url, {
			method: "PUT",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(data),
			});
			return await res.json();
		}catch(e) {
			console.log(e);
	}
}

async function rest_delete(url){
	try{
		let res = await fetch(url, {method: "DELETE"});
		return await res.text();     // res.json();
	}catch(e){
		console.log(e);
	}
}


