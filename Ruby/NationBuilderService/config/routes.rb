NationBuilderMapsService::Application.routes.draw do

  resources :power_connections

  resources :power_relay_station_types

  resources :power_relay_stations

  resources :power_grid_nodes

  resources :node_types

  resources :militarystrongholds

  resources :warehouses

  resources :cities

  resources :claims

  resources :currencies

  

  resources :users

   # get ':controller(/:action(/:xposition)(/:yposition))' 
  resources :energy_buildings

  resources :energy_building_types

  resources :resources

  resources :terraintypes

  resources :resourcetypes

  resources :uploads

  resources :tiles 
  
  resources :layers

  resources :images

  resources :maps
  
  #post '/tiles/post/fetchsections', to: 'tiles#fetchsections', as: 'fetchsections'
  get '/tiles/find/:xposition/:yposition', to: 'tiles#find', as: 'tile1' 
  #get '/users/', to: 'tiles#find', as: 'user1' 
  get '/users/names/:name', to: 'users#getuserbyname'
  get '/tiles/getscreen/:centerpositionx/:centerpositiony/:width/:height', to: 'tiles#getscreen', as: 'tiles1' 
  get '/maps/getscreen/:centerpositionx/:centerpositiony/:width/:height', to: 'maps#getscreen', as: 'map1'
  post '/maps/post/fetchsections', to: 'maps#fetchsections', as: 'fetchsections'
  
 # get  '/militarystrongholds/getscreen/:centerpositiony/:width/:height', to: 'militarystrongholds#getscreen', as: 'militarystrongholds2'  
   
  post '/militarystrongholds/createnewbase', to: 'militarystrongholds#createnewbase', as: 'militarystrongholds1'
  get '/states/first', to: 'states#first', as: 'states1'
  resources :states


  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  # You can have the root of your site routed with "root"
  # root 'welcome#index'

  # Example of regular route:
  #   get 'products/:id' => 'catalog#view'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end
end
