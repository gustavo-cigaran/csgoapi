import { http } from './api'

export default	{

	store:(grenade) => {
		return http.post('grenades', grenade);
  },

	update:(grenade) => {
		return http.put(`grenades/${grenade.id}`, grenade);
  },

  findAll:() => {
    return http.get('grenades')
  },

	delete:(grenade) => {
    console.log(JSON.stringify(grenade));
		return http.delete(`grenades/${grenade.id}`, { data: grenade })
	}

}
